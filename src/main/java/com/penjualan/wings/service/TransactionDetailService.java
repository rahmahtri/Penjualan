package com.penjualan.wings.service;

import com.penjualan.wings.dto.CheckOutGridDTO;
import com.penjualan.wings.dto.ListConfirmProductDTO;
import com.penjualan.wings.dto.ReportPenjualanDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionDetailService {
    void saveListProduct(String user);

    Page<ReportPenjualanDTO> getReportGrid(Integer page);
}
