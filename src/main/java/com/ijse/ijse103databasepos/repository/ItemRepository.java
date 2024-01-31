package com.ijse.ijse103databasepos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ijse.ijse103databasepos.entity.Item;
import com.ijse.ijse103databasepos.entity.ItemCategory;
@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("SELECT i FROM Item i WHERE i.itemCategory=:itemCategory")
    List<Item>findItemsByCategory(@Param("itemCategory")ItemCategory itemCategory); 
    
}
