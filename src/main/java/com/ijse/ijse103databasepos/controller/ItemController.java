package com.ijse.ijse103databasepos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.ijse103databasepos.dto.ItemDto;
import com.ijse.ijse103databasepos.entity.Item;
import com.ijse.ijse103databasepos.service.ItemService;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItems());
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto){
         
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDto));
         } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
         }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        Item item=itemService.getItemById(id);
        if(item!=null){
            return ResponseEntity.status(HttpStatus.OK).body(item);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id,@RequestBody ItemDto itemDto){
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemService.updateItem(id, itemDto));
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/categories/{id}/items")
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable Long id){
        return ResponseEntity.ok().body(itemService.getItemsByCategory(id));
    }
    
    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.deleteItemById(id));
        } catch (Exception e) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST).body(null);
        }
    } 
}
