package com.penjualan.wings.service;

import com.penjualan.wings.dto.CheckOutGridDTO;
import com.penjualan.wings.dto.ListConfirmProductDTO;
import com.penjualan.wings.dto.ReportPenjualanDTO;
import com.penjualan.wings.entity.Login;
import com.penjualan.wings.entity.Product;
import com.penjualan.wings.entity.TransactionDetail;
import com.penjualan.wings.entity.TransactionHeader;
import com.penjualan.wings.repository.LoginRepository;
import com.penjualan.wings.repository.ProductRepository;
import com.penjualan.wings.repository.TransactionDetailRepository;
import com.penjualan.wings.repository.TransactionHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService{

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private TransactionHeaderRepository transactionHeaderRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LoginService loginService;

    private final int rowsInPage = 3;

    @Override
    public void saveListProduct(String user) {
        String documentCode = "TRX";
        List<CheckOutGridDTO> dto = loginService.getCheckOutGrid(user);
        double total = 0;
        for (CheckOutGridDTO co : dto) {
            total = total + co.getSubTotal();
        }

        int documentNumber = 1;
        for (TransactionHeader th : transactionHeaderRepository.findAll()){
            if(th != null || th.getDocumentNumber() >= documentNumber){
                documentNumber = th.getDocumentNumber();
            }
        }

        Optional<Login> findLogin = loginRepository.findById(user);
        Login tempUser = null;
        if(findLogin.isPresent()){
            tempUser = findLogin.get();
        }

        TransactionHeader transactionHeader = new TransactionHeader(
                documentCode,
                documentNumber,
                tempUser,
                total,
                LocalDate.now()
        );
        transactionHeaderRepository.save(transactionHeader);

        for (CheckOutGridDTO co : dto){
            int quantity = (int) co.getQuantity();

            Optional<Product> findProduct = productRepository.findById(co.getProductCode());
            Product product = null;
            if(findProduct.isPresent()){
                product = findProduct.get();
            }
            TransactionDetail transactionDetail = new TransactionDetail(
                    product,
                    transactionHeader.getDocumentNumber(),
                    transactionHeader,
                    product.getPrice(),
                    co.getQuantity(),
                    co.getUnit(),
                    co.getSubTotal(),
                    product.getCurrency()
            );
            transactionDetailRepository.save(transactionDetail);
        }

        tempUser.remove();
        loginRepository.save(tempUser);
    }

    @Override
    public Page<ReportPenjualanDTO> getReportGrid(Integer page) {
        Pageable pagination = PageRequest.of(page-1, rowsInPage, Sort.by("id"));
        Page<ReportPenjualanDTO> grid = transactionDetailRepository.getGridReport(pagination);
        return grid;
    }
}
