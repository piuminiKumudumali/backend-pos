package com.ijse.ijse103databasepos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.ijse103databasepos.dto.ItemDto;
import com.ijse.ijse103databasepos.entity.Item;
 

@Service
public interface ItemService {
    List<Item> getAllItems();
    Item createItem(ItemDto itemdDto);
    Item getItemById(Long id);
    Item updateItem(Long id,ItemDto itemDto);
    List<Item> getItemsByCategory(Long id);
    String deleteItemById(Long id);
}