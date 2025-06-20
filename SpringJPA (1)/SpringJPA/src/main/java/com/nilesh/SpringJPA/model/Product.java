package com.nilesh.SpringJPA.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;


@Entity
public class Product {

    @Id
    private  int prodid;
    private  String prodname;
    private int prodprice;

    public  Product()
    {}


    public Product(int prodid, String prodname, int prodprice) {
        this.prodid = prodid;
        this.prodname = prodname;
        this.prodprice = prodprice;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public int getProdprice() {
        return prodprice;
    }

    public void setProdprice(int prodprice) {
        this.prodprice = prodprice;
    }
}
