package com.penjualan.wings.repository;

import com.penjualan.wings.dto.ReportPenjualanDTO;
import com.penjualan.wings.entity.TransactionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, String> {
    @Query(nativeQuery = true, value = """
            Select th.documentNumber
            From transactionHeader as th
            Order By th.documentNumber DESC
            Limit 1
            """)
    public String descId();

    @Query("""
            Select new com.penjualan.wings.dto.ReportPenjualanDTO(Concat(th.documentCode, '-', pro.productCode), log.username,
            th.total, th.date, pro.productName)
            From TransactionDetail as td
                Left Join td.transactionHeader as th
                Left Join th.login as log
                Left Join td.product as pro
            """)
    Page<ReportPenjualanDTO> getGridReport(Pageable pagination);
}
