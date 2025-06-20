package com.nilesh.SpringJPA.controller;

import com.nilesh.SpringJPA.model.Product;
import com.nilesh.SpringJPA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService prod;

    @GetMapping("/products")
    public List<Product> getProducts()
    {
        return prod.getproducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id)
    {
        return prod.getProductById(id);
    }


    @PostMapping("/addproduct")
    public  Product addBook(@RequestBody Product bk)
    {
        return  prod.addProduct(bk);

    }

    @PutMapping("/update")
    public void updateBook(@RequestBody Product bk)
    {
         prod.updateProduct(bk);
    }

    @DeleteMapping("/delete/{id}")
    public  String  deleteProduct(@PathVariable int id)
    {
        prod.deleteProduct(id);
        return "product Deleted Succesfuly";
    }







}
