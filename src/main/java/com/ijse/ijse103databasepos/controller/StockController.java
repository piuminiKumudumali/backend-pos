package com.ijse.ijse103databasepos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.ijse103databasepos.dto.StockDto;
import com.ijse.ijse103databasepos.entity.Stock;
import com.ijse.ijse103databasepos.service.StockService;

@RestController
@CrossOrigin("*")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock")
    public ResponseEntity<List<Stock>> getAllStock(){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAllStock());
    }


    @GetMapping("/stock/{id}")
    public ResponseEntity<Stock> getStockItemById(@PathVariable Long id){
        Stock stockItem=stockService.findStockItemById(id);
        if(stockItem!=null){
            return ResponseEntity.status(HttpStatus.OK).body(stockItem);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<Stock> updateStockItem(@RequestBody StockDto stockDto,@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(stockService.updateItemStock(stockDto,id));
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

     
}

    

