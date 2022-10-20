package com.penjualan.wings.service;

import com.penjualan.wings.dto.BuyProductDTO;
import com.penjualan.wings.dto.CheckOutGridDTO;
import com.penjualan.wings.dto.ListConfirmProductDTO;
import com.penjualan.wings.dto.ProductGridDTO;
import com.penjualan.wings.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductGridDTO> getProductGrid(String productName, Integer page);

    public Long getTotalPages(String productName);

    Product findById(String productCode);

    BuyProductDTO getDetailProduct(String productCode);
}
