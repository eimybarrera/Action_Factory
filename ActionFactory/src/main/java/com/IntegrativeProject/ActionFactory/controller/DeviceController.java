package com.IntegrativeProject.ActionFactory.controller;

import com.IntegrativeProject.ActionFactory.model.Device;
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

@RestController
@RequestMapping("api/v1/devices")
public class DeviceController {

    @Autowired
    private DeviceServiceImpl deviceServiceImpl;

    @PostMapping("/csv/upload")
    public ResponseEntity<String> uploadDevice(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CsvUtility.hasCsvFormat(file)) {
            try {
                deviceServiceImpl.save(file);
                message = "The device has been uploaded successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "The device has not been uploaded successfully: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload a CSV file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping("/device-list")
    public ResponseEntity<Map<String, Object>> getDevices() {
        Map<String, Object> respDevice = new LinkedHashMap<>();
        List<Device> deviceList = deviceServiceImpl.findAll();
        if (!deviceList.isEmpty()) {
            respDevice.put("status", 1);
            respDevice.put("data", deviceList);
            return new ResponseEntity<>(respDevice, HttpStatus.OK);
        } else {
            respDevice.clear();
            respDevice.put("status", 0);
            respDevice.put("message", "No data found");
            return new ResponseEntity<>(respDevice, HttpStatus.NOT_FOUND);
        }
    }
}
