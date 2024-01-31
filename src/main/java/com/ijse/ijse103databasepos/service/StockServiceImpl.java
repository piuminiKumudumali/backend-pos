package com.ijse.ijse103databasepos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.ijse103databasepos.dto.StockDto;
import com.ijse.ijse103databasepos.entity.Item;
import com.ijse.ijse103databasepos.entity.Stock;
 import com.ijse.ijse103databasepos.repository.ItemRepository;
import com.ijse.ijse103databasepos.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
   @Autowired
   private StockRepository stockRepository;
   @Autowired
   private ItemRepository itemRepository;
   
    
   @Override
    public List<Stock>getAllStock(){
      return stockRepository.findAll();
    }

    @Override 
    public Stock findStockItemById(Long id){
      return stockRepository.findById(id).orElse(null);
    }
    

    @Override
    public Stock updateItemStock(StockDto stockDto,Long id){
         // System.out.println("stockDto object recieved :"+stockDto+" Item id received : "+id);
         Stock stock=stockRepository.findById(id).orElse(null);
         if(stock!=null){
            stock.setQuantityOnHand(stockDto.getQuantityOnHand());
           // System.out.println("stock created : "+stock);
            stockRepository.save(stock);
            return stock;
         }else{
            return null;
         }
    }
    
   
  
}
