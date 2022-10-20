package com.penjualan.wings.service;

import com.penjualan.wings.ApplicationUserDetails;
import com.penjualan.wings.dto.BuyProductDTO;
import com.penjualan.wings.dto.CheckOutGridDTO;
import com.penjualan.wings.entity.Login;
import com.penjualan.wings.entity.Product;
import com.penjualan.wings.repository.LoginRepository;
import com.penjualan.wings.repository.ProductRepository;
import com.penjualan.wings.utility.Helper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ProductRepository productRepository;

    private String user;

    private final int rowsInPage = 100;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Login> optionalLogin = loginRepository.findById(username);

        Login tempLogin = null;
        if(optionalLogin.isPresent()){
            tempLogin = optionalLogin.get();
            this.user = tempLogin.getUsername();
        }
        return new ApplicationUserDetails(tempLogin);
    }

    @Override
    public String getUser(){
        return this.user;
    }

    @Override
    public void saveProduct(BuyProductDTO dto) {
        Optional<Login> findUser = loginRepository.findById(this.user);
        Login user = null;
        if(findUser.isPresent()){
            user = findUser.get();
        }

        Optional<Product> findProduct = productRepository.findById(dto.getProductCode());
        Product product = null;
        if(findProduct.isPresent()){
            product = findProduct.get();
        }

        user.addProduct(product);
        loginRepository.save(user);
    }

    @Override
    public List<CheckOutGridDTO> getCheckOutGrid(String user) {
        List<CheckOutGridDTO> grid = loginRepository.getCheckOutProduct(this.user);

        for (CheckOutGridDTO co : grid) {
            double price = 0;
            if(co.getDiscount() != 0){
                price = co.getPrice()-(co.getPrice()*co.getDiscount()/100);
            } else {
                price = co.getPrice();
            }

            co.setSubTotal(price*co.getQuantity());
            co.setSubTotalIndo(Helper.moneyFormat(co.getSubTotal()));
        }
        return grid;
    }

}
