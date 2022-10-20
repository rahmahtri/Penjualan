package com.penjualan.wings.controller;

import com.penjualan.wings.dto.ReportPenjualanDTO;
import com.penjualan.wings.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/transactionDetail")
public class TransactionDetailController {

    @Autowired
    private TransactionDetailService transactionDetailService;

    @GetMapping("/report")
    public String report(
            @RequestParam(defaultValue = "1") Integer page,
            Model model){
        Page<ReportPenjualanDTO> grid = transactionDetailService.getReportGrid(page);

        model.addAttribute("grid", grid);
        model.addAttribute("totalPages", grid.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("breadCrumbs", "Report Penjualan");

        return "td/td-report";
    }
}
