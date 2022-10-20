package com.penjualan.wings.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Login")
public class Login {
    @Id
    @Column(name = "Username", length = 50)
    private String username;

    @Column(name = "Password", length = 255)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "Chart",
            joinColumns = @JoinColumn(name = "Username"),
            inverseJoinColumns = @JoinColumn(name = "ProductCode")
    )
    private List<Product> productList;


    public void addProduct(Product product) {
        if(productList == null){
            productList = new LinkedList<>();
        }
        productList.add(product);
    }

    public void remove() {
        productList.clear();
    }
}
