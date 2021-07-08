package com.android.dollartoinr.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dollar {
    String date;
    double price;

    public Dollar(String date, double price) {
        this.date = date;
        this.price = price;
    }
    public Dollar()
    {

    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
