package com.microservices.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.orders.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
