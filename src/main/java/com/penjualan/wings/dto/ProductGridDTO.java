package com.penjualan.wings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGridDTO {
    private String productCode;
    private String productName;
    private double price;
    private String priceIndo;
    private String currency;
    private double discount;
    private String dimension;
    private String unit;
    private String priceAfterDiscount;
    private String empty;

    public ProductGridDTO(String productCode, String productName, double price, String currency, double discount, String dimension, String unit) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.currency = currency;
        this.discount = discount;
        this.dimension = dimension;
        this.unit = unit;
    }
}
