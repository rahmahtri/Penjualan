package com.penjualan.wings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportPenjualanDTO {
    private String transactionCode;
    private String user;
    private double total;
    private LocalDate date;
    private String item;
}
