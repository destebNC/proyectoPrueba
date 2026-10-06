package com.destebnc.supermercado.repository;

import com.destebnc.supermercado.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByInventoryId(Integer inventoryId, Pageable pageable);

    Optional<Product> findByIdAndInventoryId(Integer id, Integer inventoryId);
}
