package com.IntegrativeProject.ActionFactory.service;

import com.IntegrativeProject.ActionFactory.model.Supplier;
import com.IntegrativeProject.ActionFactory.repository.SupplierRepository;
import com.IntegrativeProject.ActionFactory.supplierExceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public void createSupplier(Supplier supplier) {
        if (supplier.getName() == null || supplier.getName().isEmpty() || supplier.getName().length() < 2) {
            throw new SupplierException("Name not valid, check field");
        }
        if (supplier.getAddress() == null || supplier.getAddress().isEmpty()) {
            throw new SupplierException("Address not valid, check field");
        }
        if (supplier.getPhoneNumber() == null || supplier.getPhoneNumber().isEmpty() || supplier.getPhoneNumber().length() < 10) {
            throw new SupplierException("Phone number not valid, check field");
        }
        if (supplier.getEmail() == null || supplier.getEmail().isEmpty() || !supplier.getEmail().contains("@")) {
            throw new SupplierException("Email not valid, check field");
        }
        if (supplier.getWebsite() == null || supplier.getWebsite().isEmpty() || !supplier.getWebsite().startsWith("http")) {
            throw new SupplierException("Website not valid, check field");
        }
        if (supplier.getIndustrySector() == null || supplier.getIndustrySector().isEmpty()) {
            throw new SupplierException("Industry sector not valid, check field");
        }
        if (supplier.getRegistrationDate() == null) {
            throw new SupplierException("Registration date not valid, check field");
        }

        this.supplierRepository.save(supplier);
    }
    public Supplier getSupplierById(Long id){
        Optional<Supplier> optionalSupplier = this.supplierRepository.findById(id);
        return optionalSupplier.orElseGet(Supplier::new);
    }

    public List<Supplier> getAllSuppliers(){return this.supplierRepository.findAll();}

    public void deleteSupplier(Long id){this.supplierRepository.deleteById(id);}

    public Supplier updateSupplier(Supplier supplier){
        Optional<Supplier> optionalSupplier = this.supplierRepository.findById(supplier.getId());
        if(optionalSupplier.isPresent()){
            Supplier existingSupplier = optionalSupplier.get();
            existingSupplier.setName(supplier.getName());
            existingSupplier.setAddress(supplier.getAddress());
            existingSupplier.setPhoneNumber(supplier.getPhoneNumber());
            existingSupplier.setEmail(supplier.getEmail());
            existingSupplier.setWebsite(supplier.getWebsite());
            existingSupplier.setIndustrySector(supplier.getIndustrySector());
            existingSupplier.setRegistrationDate(supplier.getRegistrationDate());

            return this.supplierRepository.save(existingSupplier);
        }
        return new Supplier();
    }
}
