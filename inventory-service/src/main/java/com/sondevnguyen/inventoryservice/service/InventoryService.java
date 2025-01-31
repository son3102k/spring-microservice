package com.sondevnguyen.inventoryservice.service;

import com.sondevnguyen.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<String> skuCode);
}
