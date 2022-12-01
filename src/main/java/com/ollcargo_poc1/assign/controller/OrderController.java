package com.ollcargo_poc1.assign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ollcargo_poc1.assign.model.Order;
import com.ollcargo_poc1.assign.repository.OrderRepository;

@RestController
public class OrderController {

	  	@Autowired
	    OrderRepository orderRepo;
	  	
	  	@GetMapping("/test")
	    public String hello() {
	        return "hello world";
	    }

	    @GetMapping("/order")
	    public List<Order> viewOrders() {
	        return orderRepo.findAll();
	    }
	
}
