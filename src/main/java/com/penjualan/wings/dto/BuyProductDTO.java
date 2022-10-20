package com.penjualan.wings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyProductDTO {
    private String productCode;
    private String productName;
    private double price;
    private String dimension;
    private String unit;
    private double discount;
}
