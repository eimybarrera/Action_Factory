package com.IntegrativeProject.ActionFactory.service;

import com.IntegrativeProject.ActionFactory.model.Supplier;
import com.IntegrativeProject.ActionFactory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public void createSupplier(Supplier supplier){
        this.supplierRepository.save(supplier);
    }
    public Supplier getSupplierById(int id){
        Optional<Supplier> optionalSupplier = this.supplierRepository.findById(id);
        if(optionalSupplier.isPresent()){
            return optionalSupplier.get();
        }
        return new Supplier();
    }

    public List<Supplier> getAllSuppliers(){return this.supplierRepository.findAll();}

    public void deleteSupplier(int id){this.supplierRepository.deleteById(id);}

    public Supplier updateSupplier(Supplier supplier){
        Optional<Supplier> optionalSupplier = this.supplierRepository.findById(supplier.getId());
        if(optionalSupplier.isPresent()){
            Supplier existingSupplier = optionalSupplier.get();
            existingSupplier.setName(supplier.getName());
            existingSupplier.setAddress(supplier.getAddress());
            existingSupplier.setPhoneNumber(supplier.getPhoneNumber());
            existingSupplier.setEmali(supplier.getEmali());
            existingSupplier.setWebSite(supplier.getWebSite());
            existingSupplier.setIndustrySector(supplier.getIndustrySector());
            existingSupplier.setJoiningDate(supplier.getJoiningDate());

            return this.supplierRepository.save(existingSupplier);
        }
        return new Supplier();
    }
}
