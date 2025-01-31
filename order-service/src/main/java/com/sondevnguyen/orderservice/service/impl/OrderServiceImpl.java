package com.sondevnguyen.orderservice.service.impl;

import com.sondevnguyen.orderservice.config.WebClientConfig;
import com.sondevnguyen.orderservice.dto.InventoryResponse;
import com.sondevnguyen.orderservice.dto.OrderRequest;
import com.sondevnguyen.orderservice.model.Order;
import com.sondevnguyen.orderservice.model.OrderLineItems;
import com.sondevnguyen.orderservice.repository.OrderRepository;
import com.sondevnguyen.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    @Override
    public void placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        if (!CollectionUtils.isEmpty(request.getOrderLineItemsDtoList())) {
            order.setOrderLineItemsList(request.getOrderLineItemsDtoList()
                    .stream()
                    .map(OrderService::mapToDto).collect(Collectors.toList()));
        }

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();


        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8083/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allIsInStock = null != inventoryResponseArray && Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::getIsInStock);
        if (allIsInStock) {
            orderRepository.save(order);
        }
        else {
            throw new IllegalStateException("Product is not in stock. Try again later.");
        }
    }


}
