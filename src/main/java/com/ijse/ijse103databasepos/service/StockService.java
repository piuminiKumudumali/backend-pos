package com.ijse.ijse103databasepos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.ijse103databasepos.dto.StockDto;
import com.ijse.ijse103databasepos.entity.Stock;

@Service
public interface StockService {
    List<Stock> getAllStock();
    Stock findStockItemById(Long id);
    Stock updateItemStock(StockDto stockdDto,Long id);
     
}
