package com.penjualan.wings.service;

import com.penjualan.wings.dto.BuyProductDTO;
import com.penjualan.wings.dto.CheckOutGridDTO;
import com.penjualan.wings.dto.ListConfirmProductDTO;
import com.penjualan.wings.dto.ProductGridDTO;
import com.penjualan.wings.entity.Login;
import com.penjualan.wings.entity.Product;
import com.penjualan.wings.repository.LoginRepository;
import com.penjualan.wings.repository.ProductRepository;
import com.penjualan.wings.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LoginRepository loginRepository;

    private final int rowsInPage = 3;

    @Override
    public List<ProductGridDTO> getProductGrid(String productName, Integer page) {
        Pageable pagination = PageRequest.of(page-1, rowsInPage, Sort.by("id"));
        List<ProductGridDTO> grid = productRepository.findAllProduct(productName, pagination);
        for (ProductGridDTO product : grid){
            product.setPriceIndo(Helper.moneyFormat(product.getPrice()));
            product.setPriceAfterDiscount(Helper.moneyFormat(product.getPrice()-(product.getPrice() * (product.getDiscount()/100))));
            product.setEmpty("");
        }
        return grid;
    }

    @Override
    public Long getTotalPages(String productName) {
        double totalData = (double) (productRepository.countProduct(productName));
        long totalPage = (long) (Math.ceil(totalData/rowsInPage));
        return totalPage;
    }

    @Override
    public Product findById(String productCode) {
        Optional<Product> findProduct = productRepository.findById(productCode);
        Product product = null;

        if(findProduct.isPresent()){
            product = findProduct.get();
        }
        return product;
    }

    @Override
    public BuyProductDTO getDetailProduct(String productCode) {
        Optional<Product> findProduct = productRepository.findById(productCode);
        Product product = findProduct.get();

        BuyProductDTO productDTO = new BuyProductDTO(
                product.getProductCode(),
                product.getProductName(),
                product.getPrice(),
                product.getDimension(),
                product.getUnit(),
                product.getDiscount()
        );
        return productDTO;
    }



}
