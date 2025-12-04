package com.condominio.ConvivoApp.supplier.repository;

import com.condominio.ConvivoApp.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByCategory(String category);
    List<Supplier> findByBusinessNameContainingIgnoreCase(String businessName);
    List<Supplier> findByRatingGreaterThanEqual(Double rating);
}