package com.ijse.ijse103databasepos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.ijse103databasepos.entity.ItemCategory;
import com.ijse.ijse103databasepos.service.ItemCategoryService;

@RestController
@CrossOrigin(origins = "*")
public class ItemCategoryController {
    
    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<ItemCategory>> getAllItemCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(itemCategoryService.getAllItemCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<ItemCategory> createItemCategory(@RequestBody ItemCategory itemCategory){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemCategoryService.createItemCategory(itemCategory));
        } catch (Exception e) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<ItemCategory> getCategoryById(@PathVariable Long id){
        ItemCategory itemCategory=itemCategoryService.findItemCategoryById(id);

        if(itemCategory!=null){
            return ResponseEntity.status(HttpStatus.OK).body(itemCategory);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } 
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<ItemCategory> updateItemCategory (@PathVariable Long id,@RequestBody ItemCategory itemCategory){
       // return itemCategoryService.updateItemCategory(id, itemCategory);
       try {
            return ResponseEntity.status(HttpStatus.OK).body(itemCategoryService.updateItemCategory(id, itemCategory));
       } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
       }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteItemCategory(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemCategoryService.deleteItemCategory(id));
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
