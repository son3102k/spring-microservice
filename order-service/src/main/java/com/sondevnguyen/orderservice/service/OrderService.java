package com.sondevnguyen.orderservice.service;

import com.sondevnguyen.orderservice.dto.OrderLineItemsDto;
import com.sondevnguyen.orderservice.dto.OrderRequest;
import com.sondevnguyen.orderservice.model.OrderLineItems;

public interface OrderService {
    static OrderLineItems mapToDto(OrderLineItemsDto x) {
        OrderLineItems item = new OrderLineItems();
        item.setPrice(x.getPrice());
        item.setQuantity(x.getQuantity());
        item.setSkuCode(x.getSkuCode());
        return item;
    }

    void placeOrder(OrderRequest request);
}
