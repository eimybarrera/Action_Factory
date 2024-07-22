package com.IntegrativeProject.ActionFactory.service;

import com.IntegrativeProject.ActionFactory.model.Device;
import com.IntegrativeProject.ActionFactory.model.InvalidDevice;
import com.IntegrativeProject.ActionFactory.model.ValidDevice;
import com.IntegrativeProject.ActionFactory.repository.*;
import com.IntegrativeProject.ActionFactory.util.CsvUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    InvalidDeviceRepository invalidDeviceRepository;

    @Autowired
    ValidDeviceRepository validDeviceRepository;

    // Método para almacenar los datos CSV en la base de datos
    @Transactional
    @Override
    public void save(MultipartFile file) {
        try {
            CsvUtility csvUtility = new CsvUtility(employeeRepository, supplierRepository);
            List<InvalidDevice> invalidDeviceList = new ArrayList<>();
            List<ValidDevice> validDeviceList = new ArrayList<>();
            List<Device> deviceList = csvUtility.csvToDeviceList(file.getInputStream());

            // Ordenar lista de dispositivos por IMEI de forma ascendente
            List<Device> sortedDeviceList = deviceList.stream()
                    .sorted((d1, d2) -> d1.getImei().compareTo(d2.getImei()))
                    .collect(Collectors.toList());
            deviceRepository.saveAll(sortedDeviceList);

            // Validación de dispositivos
            for (Device device : sortedDeviceList) {
                // Validación de la existencia del proveedor en la base de datos
                if (supplierRepository.existsById(device.getSupplier().getId())) {
                    // Validación de estado
                    if (device.getStatus().equalsIgnoreCase("READY_TO_USE")) {
                        // Validación de puntaje
                        if (device.getScore() > 60) {
                            // Validación de IMEI no palíndromo
                            //Conversión del tipo de dato de IMEI de Long a String
                            String imeiStr = Long.toString(device.getImei());
                            //Creación del String IMEI reverso
                            String reverseImeiStr = new StringBuilder(imeiStr).reverse().toString();
                            if (!imeiStr.equals(reverseImeiStr)) {
                                System.out.println("The device with imei: " + device.getImei() + " has been successfully validated");
                                validDeviceList.add(new ValidDevice(device));
                            } else {
                                System.out.println("The device with imei: " + device.getImei() + " has an imei palindrome");
                                invalidDeviceList.add(new InvalidDevice(device));
                            }
                        } else {
                            System.out.println("The device with imei: " + device.getImei() + " has a score less than or equal to 60");
                            invalidDeviceList.add(new InvalidDevice(device));
                        }
                    } else {
                        System.out.println("The device with imei: " + device.getImei() + " has a status equal to CANCELLED");
                        invalidDeviceList.add(new InvalidDevice(device));
                    }
                } else {
                    invalidDeviceList.add(new InvalidDevice(device));
                    throw new RuntimeException("Supplier with ID " + device.getSupplier().getId() + " does not exist.");

                }
            }

            // Guardar listas de dispositivos válidos e inválidos en la base de datos
            validDeviceRepository.saveAll(validDeviceList);
            invalidDeviceRepository.saveAll(invalidDeviceList);

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error processing devices: " + e.getMessage());
        }
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }
}
