package com.ijse.ijse103databasepos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ijse.ijse103databasepos.entity.Order;

@Service
public interface OrderRepository extends JpaRepository<Order,Long>{
    
}
