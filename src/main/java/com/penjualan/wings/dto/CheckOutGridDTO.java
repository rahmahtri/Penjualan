package com.penjualan.wings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutGridDTO {
    private String productCode;
    private String productName;
    private long quantity;
    private String unit;
    private double price;
    private double subTotal;
    private String subTotalIndo;
    private double discount;

    public CheckOutGridDTO(String productCode, String productName, long quantity, String unit, double price, double discount) {
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
    }
}
