package com.microservices.orders.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.microservices.orders.client.InventoryClient;
import com.microservices.orders.dto.OrderRequest;
import com.microservices.orders.dto.OrderResponse;
import com.microservices.orders.model.Order;
import com.microservices.orders.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    
    public OrderResponse createOrder(OrderRequest orderRequest) {

        boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (!isProductInStock)
            throw new RuntimeException("There are not " + orderRequest.quantity()
                + " unities of the product with sku code " + orderRequest.skuCode() + " in stock.");

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuCode(orderRequest.skuCode())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();

        order = orderRepository.save(order);

        log.info("Order created successfully with ID {}", order.getId());
    
        return new OrderResponse(order.getId(), order.getOrderNumber(), 
                order.getSkuCode(), order.getPrice(), order.getQuantity());
    }
}
