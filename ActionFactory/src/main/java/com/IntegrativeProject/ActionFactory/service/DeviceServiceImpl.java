package com.IntegrativeProject.ActionFactory.service;

import com.IntegrativeProject.ActionFactory.model.Device;
import com.IntegrativeProject.ActionFactory.repository.DeviceRepository;
import com.IntegrativeProject.ActionFactory.util.CsvUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService{

//  Método para almacernar los datos CSV en la base de datos
    @Autowired
    DeviceRepository deviceRepository;
    @Override
    public void save(MultipartFile file) {
        try {
            List<Device> deviceList = CsvUtility.csvToDeviceList(file.getInputStream());
            deviceRepository.saveAll(deviceList);
        } catch (IOException e) {
            throw new RuntimeException("Data is not successfully: " + e.getMessage());
        }
    }

//  Método que lee la base de datos y devuelve una lista de dispositivos
    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }
}
