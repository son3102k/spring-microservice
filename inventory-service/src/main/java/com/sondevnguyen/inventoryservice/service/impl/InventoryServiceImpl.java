package com.sondevnguyen.inventoryservice.service.impl;

import com.sondevnguyen.inventoryservice.dto.InventoryResponse;
import com.sondevnguyen.inventoryservice.repository.InventoryRepository;
import com.sondevnguyen.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findAllBySkuCodeIn(skuCode).stream().map(
                x -> InventoryResponse.builder().skuCode(x.getSkuCode()).isInStock(x.getQuantity() > 0).build()
        ).collect(Collectors.toList());
    }
}
