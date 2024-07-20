package com.IntegrativeProject.ActionFactory.controller;

import com.IntegrativeProject.ActionFactory.model.Supplier;
import com.IntegrativeProject.ActionFactory.service.SupplierService;
import com.IntegrativeProject.ActionFactory.Exceptions.SupplierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }
    @PostMapping()
    public ResponseEntity<String> createSupplier(@RequestBody Supplier supplier) {
        try {
            this.supplierService.createSupplier(supplier);
            return ResponseEntity.status(HttpStatus.CREATED).body("Supplier created successfully");
        }catch (SupplierException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(this.supplierService.getSupplierById(id));
        } catch (SupplierException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllSuppliers(){
        try {
            return ResponseEntity.ok(this.supplierService.getAllSuppliers());
        }catch(SupplierException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable("id") Long id){
        try{
            this.supplierService.deleteSupplier(id);
            return ResponseEntity.ok("Supplier deleted successfully");
        }catch (SupplierException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateSupplier(@PathVariable("id") Long id, @RequestBody Supplier supplier){
        supplier.setId(id);
        try {
            Supplier updatedSupplier = this.supplierService.updateSupplier(supplier);
            return ResponseEntity.status(HttpStatus.CREATED).body("Supplier updated successfully");
        }catch (SupplierException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

