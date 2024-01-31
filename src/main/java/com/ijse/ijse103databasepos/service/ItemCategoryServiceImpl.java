package com.ijse.ijse103databasepos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ijse.ijse103databasepos.entity.ItemCategory;
import com.ijse.ijse103databasepos.repository.ItemCategoryRepository;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<ItemCategory> getAllItemCategories(){
        return itemCategoryRepository.findAll();
    }
    @Override
    public ItemCategory findItemCategoryById(Long id){
        return itemCategoryRepository.findById(id).orElse(null);
    }
    @Override
    public ItemCategory createItemCategory(ItemCategory itemCategory){
        return itemCategoryRepository.save(itemCategory);
    }
    @Override
    public ItemCategory updateItemCategory(Long id,ItemCategory itemCategory){
        ItemCategory existingItemCategory=itemCategoryRepository.findById(id).orElse(null);
        if(existingItemCategory!=null){
            existingItemCategory.setName(itemCategory.getName());
            return itemCategoryRepository.save(existingItemCategory);
        }else{
            return null;
        }
    }
    @Override
    public String deleteItemCategory(Long id){
        ItemCategory itemCategory=itemCategoryRepository.findById(id).orElse(null);

        if(itemCategory!=null){
            itemCategoryRepository.deleteById(id);
            return "Item Category deleted";
        }else{
            return "Item Category not found";
        }
    }
    
}
