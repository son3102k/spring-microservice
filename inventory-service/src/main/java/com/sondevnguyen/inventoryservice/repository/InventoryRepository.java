package com.sondevnguyen.inventoryservice.repository;

import com.sondevnguyen.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory>findAllBySkuCodeIn(List<String> skuCode);
}
