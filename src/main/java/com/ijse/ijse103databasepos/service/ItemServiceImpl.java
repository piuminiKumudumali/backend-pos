package com.ijse.ijse103databasepos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.ijse103databasepos.dto.ItemDto;
import com.ijse.ijse103databasepos.entity.Item;
import com.ijse.ijse103databasepos.entity.ItemCategory;
import com.ijse.ijse103databasepos.entity.Stock;
import com.ijse.ijse103databasepos.repository.ItemCategoryRepository;
import com.ijse.ijse103databasepos.repository.ItemRepository;

 
 
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @Override 
    public Item createItem(ItemDto itemDto){
       // System.out.println("Received ItemDto: " + itemDto);
         ItemCategory itemCategory=itemCategoryRepository.findById(itemDto.getCategoryId()).orElse(null);
        // System.out.println("ItemCategory retrieved: " + itemCategory);
        if(itemCategory!=null){
            Item item=new Item();
            item.setItemName(itemDto.getName());
            item.setUnit(itemDto.getUnit());
            item.setUnitPrice(itemDto.getPrice());
            item.setItemCategory(itemCategory);

            Stock stock=new Stock();
            stock.setItem(item);
            stock.getQuantityOnHand();
            item.setStock(stock); 

            // Logging for debugging
           // System.out.println("Creating Item: " + item);
            return itemRepository.save(item);
        }else{
            //System.out.println("ItemCategory not found. Cannot create item.");
            return null;
        }
    }

    @Override
    public Item getItemById(Long id){
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item updateItem(Long id,ItemDto itemDto){
        
        Item existingItem=itemRepository.findById(id).orElse(null);
        ItemCategory itemCategory=itemCategoryRepository.findById(itemDto.getCategoryId()).orElse(null);
        if(existingItem!=null){

            System.out.println("Updating item with id: " + id);
            System.out.println("Existing Item: " + existingItem);
            System.out.println("ItemCategory: " + itemCategory);

            existingItem.setItemName(itemDto.getName());
            existingItem.setUnit(itemDto.getUnit());
            existingItem.setItemCategory(itemCategory);
            existingItem.setUnitPrice(itemDto.getPrice());

            Item updatedItem=itemRepository.save(existingItem);

            System.out.println("Updated Item: " + updatedItem);

            return updatedItem;
        }else{
            return null;
        }
    }

    @Override
    public List<Item> getItemsByCategory(Long id){
        ItemCategory itemCategory=itemCategoryRepository.findById(id).orElse(null);
        if(itemCategory!=null){
            return itemRepository.findItemsByCategory(itemCategory);
        }else{
            return null;
        }

    }
    @Override 
    public String deleteItemById(Long id){
        Item item=itemRepository.findById(id).orElse(null);
        if(item!=null){
            itemRepository.deleteById(id);
            return "Item deleted";
        }else{
            return "Item not found";
        }
    }
     
    
}
