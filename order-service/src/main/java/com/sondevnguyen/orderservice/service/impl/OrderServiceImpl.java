package com.sondevnguyen.orderservice.service.impl;

import com.sondevnguyen.orderservice.dto.OrderRequest;
import com.sondevnguyen.orderservice.model.Order;
import com.sondevnguyen.orderservice.repository.OrderRepository;
import com.sondevnguyen.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        if (!CollectionUtils.isEmpty(request.getOrderLineItemsDtoList())) {
            order.setOrderLineItemsList(request.getOrderLineItemsDtoList()
                    .stream()
                    .map(OrderService::mapToDto).collect(Collectors.toList()));
        }

        orderRepository.save(order);
    }


}
