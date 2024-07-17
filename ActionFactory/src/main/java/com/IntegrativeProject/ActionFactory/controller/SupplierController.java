package com.IntegrativeProject.ActionFactory.controller;

import com.IntegrativeProject.ActionFactory.model.Supplier;
import com.IntegrativeProject.ActionFactory.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }
    @PostMapping()
    public ResponseEntity<String> createSupplier(@RequestBody Supplier supplier){
        this.supplierService.createSupplier(supplier);
        return ResponseEntity.ok("Supplier created succesfully");
    }

    @GetMapping("{id}")
    public Supplier getSupplierById(@PathVariable("id") Long id){
        return this.supplierService.getSupplierById(id);
    }

    @GetMapping()
    public List<Supplier> getAllSuppliers(){return this.supplierService.getAllSuppliers();}

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable("id") Long id){
        this.supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Supplier deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") Long id, @RequestBody Supplier supplier){
        supplier.setId(id);
        Supplier updatedSupplier = this.supplierService.updateSupplier(supplier);
        return ResponseEntity.ok(updatedSupplier);
    }

}

