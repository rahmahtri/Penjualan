package com.penjualan.wings.repository;

import com.penjualan.wings.entity.TransactionHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHeaderRepository extends JpaRepository<TransactionHeader, String> {
}
