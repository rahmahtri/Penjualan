package com.penjualan.wings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListConfirmProductDTO {
    private List<CheckOutGridDTO> checkOutGridDTOS;
}
