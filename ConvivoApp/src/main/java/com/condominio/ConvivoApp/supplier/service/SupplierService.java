package com.condominio.ConvivoApp.supplier.service;

import com.condominio.ConvivoApp.supplier.entity.Supplier;
import com.condominio.ConvivoApp.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> searchSuppliers(String category, String businessName, Double rating) {
        List<Supplier> suppliers = supplierRepository.findAll();
        // Filtro personalizzato
        return suppliers;
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow();
    }

    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
