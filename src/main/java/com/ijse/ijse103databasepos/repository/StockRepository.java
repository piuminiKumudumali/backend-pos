package com.ijse.ijse103databasepos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.ijse103databasepos.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long>{
    
}
