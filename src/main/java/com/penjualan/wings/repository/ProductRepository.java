package com.penjualan.wings.repository;

import com.penjualan.wings.dto.CheckOutGridDTO;
import com.penjualan.wings.dto.ProductGridDTO;
import com.penjualan.wings.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("""
            Select new com.penjualan.wings.dto.ProductGridDTO(pro.productCode, pro.productName, pro.price, pro.currency,
            pro.discount, pro.dimension, pro.unit)
            From Product as pro
            Where pro.productName like %:productName%
            """)
    List<ProductGridDTO> findAllProduct(@Param("productName") String productName,
                                        Pageable pagination);

    @Query("""
            Select Count(pro.productName)
            From Product as pro
            Where pro.productName like %:productName%
            """)
    public Long countProduct(@Param("productName") String productName);

//    @Query("""
//            Select new com.penjualan.wings.dto.CheckOutGridDTO(pro.productName, pro.unit, pro.price)
//            From TransactionDetail as td
//            Left Join td.productCode as pro
//            Left Join td.documentCode as th
//            Where pro.productName like %:productName%
//            """)
//    List<CheckOutGridDTO> getCheckoutProduct(@Param("productName") String productName, Pageable pagination);
}
