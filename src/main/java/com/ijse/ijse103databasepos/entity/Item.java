package com.ijse.ijse103databasepos.entity;

 
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
 
@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    private String unit;

    @Column(nullable = false)
    private Double unitPrice;
    
    @ManyToOne  
    @JoinColumn(name = "category_id")
    private ItemCategory itemCategory;

    @JsonIgnore
    @ManyToMany(mappedBy = "items",cascade = CascadeType.ALL)
    private Set<Order> orders=new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "item",cascade = CascadeType.ALL)
    private Stock stock;
}


