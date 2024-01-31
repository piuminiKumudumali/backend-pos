package com.ijse.ijse103databasepos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String name;
    private String unit;
    private Double price;
    private Long categoryId;
    private StockDto stockDto;
}
