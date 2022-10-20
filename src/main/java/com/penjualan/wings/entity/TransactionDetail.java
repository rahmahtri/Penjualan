package com.penjualan.wings.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TransactionDetail {

    @EmbeddedId
    private TransactionDetailKey id;

    @ManyToOne
    @MapsId("productCode")
    @JoinColumn(name = "ProductCode")
    private Product product;

    @Column(name = "DocumentNumber")
    private int documentNumber;

    @ManyToOne
    @MapsId("documentCode")
    @JoinColumn(name = "DocumentCode")
    private TransactionHeader transactionHeader;

    @Column(name = "Price")
    private double price;

    @Column(name = "Quantity")
    private long quantity;

    @Column(name = "Unit")
    private String unit;

    @Column(name = "SubTotal")
    private double subTotal;

    @Column(name = "Currency")
    private String currency;

    public TransactionDetail(Product product, int documentNumber, TransactionHeader transactionHeader, double price,
                             long quantity, String unit, double subTotal, String currency) {
        this.id = new TransactionDetailKey(product.getProductCode(), transactionHeader.getDocumentCode());
        this.product = product;
        this.documentNumber = documentNumber;
        this.transactionHeader = transactionHeader;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.subTotal = subTotal;
        this.currency = currency;
    }
}
