package com.penjualan.wings.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class TransactionDetailKey implements Serializable {

    @Column(name = "ProductCode")
    private String productCode;

    @Column(name = "DocumentCode")
    private String documentCode;

    public TransactionDetailKey(String productCode, String documentCode) {
        this.productCode = productCode;
        this.documentCode = documentCode;
    }
}
