package com.penjualan.wings.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "ProductCode", length = 18)
    private String productCode;
    @Column(name = "ProductName", length = 30)
    private String productName;
    @Column(name = "Price", length = 6)
    private double price;
    @Column(name = "Currency", length = 5)
    private String currency;
    @Column(name = "Discount", length = 6)
    private double discount;
    @Column(name = "Dimension", length = 50)
    private String dimension;
    @Column(name = "Unit", length = 5)
    private String unit;

    @ManyToMany
    @JoinTable(
            name = "Chart",
            joinColumns = @JoinColumn(name = "ProductCode"),
            inverseJoinColumns = @JoinColumn(name = "Username")
    )
    private List<Login> logins;

    @OneToMany(mappedBy = "product")
    private List<TransactionDetail> details;
}
