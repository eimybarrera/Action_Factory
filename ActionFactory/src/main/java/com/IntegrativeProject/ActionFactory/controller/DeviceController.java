package com.IntegrativeProject.ActionFactory.controller;

import com.IntegrativeProject.ActionFactory.model.Device;
import com.IntegrativeProject.ActionFactory.repository.EmployeeRepository;
import com.IntegrativeProject.ActionFactory.repository.SupplierRepository;
import com.IntegrativeProject.ActionFactory.service.DeviceServiceImpl;
import com.IntegrativeProject.ActionFactory.util.CsvUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
;
;




@RestController
@RequestMapping("api/v1/devices")
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceServiceimpl;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDevice(@RequestParam("file") MultipartFile file){
        String message = "";
        if (CsvUtility.hasCsvFormat(file)) {
            try {
                deviceServiceimpl.save(file);
                message = "The device is uploaded successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "The device is not upload successfully: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload an csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }


    @GetMapping("/devevice-list")
    public ResponseEntity < String > getDevices() {
        Map< String, Object > respDevice = new LinkedHashMap< String, Object >();
        List<Device> deviceList = deviceServiceimpl.findAll();
        if (!deviceList.isEmpty()) {
            respDevice.put("status", 1);
            respDevice.put("data", deviceList);
            return new ResponseEntity < String> (respDevice.toString(), HttpStatus.OK);
        } else {
            respDevice.clear();
            respDevice.put("status", 0);
            respDevice.put("message", "Data is not found");
            return new ResponseEntity <String>(respDevice.toString(), HttpStatus.NOT_FOUND);
        }
    }

    }





