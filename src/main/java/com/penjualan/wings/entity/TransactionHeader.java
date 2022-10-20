package com.penjualan.wings.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TransactionHeader")
public class TransactionHeader {
    @Id
    @Column(name = "DocumentCode", length = 3)
    private String documentCode;
    @Column(name = "DocumentNumber", length = 10)
    private Integer documentNumber;
    @OneToOne
    @JoinColumn(name = "[User]")
    private Login login;
    @Column(name = "Total", length = 10)
    private double total;
    @Column(name = "Date", length = 10)
    private LocalDate date;

    @OneToMany(mappedBy = "transactionHeader")
    private List<TransactionDetail> details;

    public TransactionHeader(String documentCode, Integer documentNumber, Login login, double total, LocalDate date) {
        this.documentCode = documentCode;
        this.documentNumber = documentNumber;
        this.login = login;
        this.total = total;
        this.date = date;
    }
}
