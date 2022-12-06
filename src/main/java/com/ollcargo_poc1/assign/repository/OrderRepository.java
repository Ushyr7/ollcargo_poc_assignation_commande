package com.ollcargo_poc1.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ollcargo_poc1.assign.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
    Order findFirstByOrderById();
}
