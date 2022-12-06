package com.ollcargo_poc1.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ollcargo_poc1.assign.model.DeliveryPerson;

public interface DeliveryPersonRespository extends JpaRepository<DeliveryPerson, String> {
    
}
