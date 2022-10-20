package com.penjualan.wings.service;

import com.penjualan.wings.dto.BuyProductDTO;
import com.penjualan.wings.dto.CheckOutGridDTO;

import java.util.List;

public interface LoginService {
    void saveProduct(BuyProductDTO dto);

    List<CheckOutGridDTO> getCheckOutGrid(String user);
    public String getUser();

}
