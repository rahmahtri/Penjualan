package com.penjualan.wings.controller;

import com.penjualan.wings.dto.BuyProductDTO;
import com.penjualan.wings.dto.CheckOutGridDTO;
import com.penjualan.wings.dto.ListConfirmProductDTO;
import com.penjualan.wings.dto.ProductGridDTO;
import com.penjualan.wings.entity.Product;
import com.penjualan.wings.service.LoginService;
import com.penjualan.wings.service.ProductService;
import com.penjualan.wings.service.TransactionDetailService;
import com.penjualan.wings.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private TransactionDetailService transactionDetailService;

    @GetMapping("/list")
    public String productList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "") String productName,
            Model model) {
        List<ProductGridDTO> gridDTOS = productService.getProductGrid(productName, page);
       long totalPages = productService.getTotalPages(productName);

        model.addAttribute("grid", gridDTOS);
        model.addAttribute("productName", productName);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("breadCrumbs", "Product List");

        return "product/product-list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) String productCode,
                         Model model){
        BuyProductDTO dto = productService.getDetailProduct(productCode);

        double priceAfterDiscount = dto.getPrice() - ((dto.getPrice() * dto.getDiscount())/100);
        String dimension = dto.getDimension();
        String unit = dto.getUnit();
        double discount = dto.getDiscount();
        String empty = "";

        model.addAttribute("product", dto);
        model.addAttribute("priceAfterDiscount", Helper.moneyFormat(priceAfterDiscount));
        model.addAttribute("discount", discount);
        model.addAttribute("empty", empty);
        model.addAttribute("breadCrumbs", "Product Detail");

        return "product/product-detail";
    }

    @PostMapping("/buy")
    public String buyProduct(@ModelAttribute("product") BuyProductDTO dto){
        loginService.saveProduct(dto);
        return "redirect:/product/list";
    }

    @GetMapping("/checkOut")
    public String getCheckOutPage(
            Model model){
        String user = loginService.getUser();

        List<CheckOutGridDTO> grid = loginService.getCheckOutGrid(user);
        double total = 0;

        for (CheckOutGridDTO val : grid){
            total = total + val.getSubTotal();
        }

        model.addAttribute("grid", grid);
        model.addAttribute("user", user);
        model.addAttribute("total", Helper.moneyFormat(total));
        model.addAttribute("breadCrumbs", "Check Out Page");
        return "product/product-checkOut";
    }

    @GetMapping("/confirm")
    public String confirmListProduct(@RequestParam(required = true) String user){
        transactionDetailService.saveListProduct(user);
        return "redirect:/product/l66ist";
    }


}