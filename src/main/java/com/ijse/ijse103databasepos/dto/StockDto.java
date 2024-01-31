package com.ijse.ijse103databasepos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {
    private Long itemId;
    private int quantityOnHand;
}
