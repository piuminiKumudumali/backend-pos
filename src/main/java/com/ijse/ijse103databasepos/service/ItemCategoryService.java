package com.ijse.ijse103databasepos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.ijse103databasepos.entity.ItemCategory;

@Service
public interface ItemCategoryService {
    List<ItemCategory> getAllItemCategories();
    ItemCategory findItemCategoryById(Long id);
    ItemCategory createItemCategory(ItemCategory itemCategory);
    ItemCategory updateItemCategory(Long id,ItemCategory itemCategory);
    String deleteItemCategory(Long id);
    
}
