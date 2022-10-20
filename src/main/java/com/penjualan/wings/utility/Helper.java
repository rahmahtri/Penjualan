package com.penjualan.wings.utility;

import java.text.NumberFormat;
import java.util.Locale;

public class Helper {

    public static String moneyFormat(double price){
        Locale indo = new Locale("id","ID");
        return NumberFormat.getCurrencyInstance(indo).format(price);
    }
}
