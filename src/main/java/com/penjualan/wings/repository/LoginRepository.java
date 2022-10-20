package com.penjualan.wings.repository;

import com.penjualan.wings.dto.CheckOutGridDTO;
import com.penjualan.wings.entity.Login;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login, String> {

    @Query("""
            Select new com.penjualan.wings.dto.CheckOutGridDTO(pro.productCode, pro.productName, count(pro.productCode), pro.unit, 
            pro.price, pro.discount)
            From Product as pro
            Left Join pro.logins as log
            Where log.username like %:user%
            Group by pro.productCode, pro.productName, pro.unit, pro.price, pro.discount
            """)
    List<CheckOutGridDTO> getCheckOutProduct(@Param("user") String user);
}
