package com.IntegrativeProject.ActionFactory.service;

import com.IntegrativeProject.ActionFactory.model.Supplier;
import com.IntegrativeProject.ActionFactory.repository.SupplierRepository;
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

    public void createSupplier(Supplier supplier){
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
