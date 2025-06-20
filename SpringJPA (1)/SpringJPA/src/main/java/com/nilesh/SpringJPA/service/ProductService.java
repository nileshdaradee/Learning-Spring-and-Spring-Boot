package com.nilesh.SpringJPA.service;

import com.nilesh.SpringJPA.model.Product;
import com.nilesh.SpringJPA.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    public List<Product> getproducts()
    {
        return repo.findAll();
    }

    public Product getProductById(int id)
    {
        return  repo.findById(id).get();
    }

    public Product addProduct(Product prod)
    {
        repo.save(prod);
        return prod;
    }


    public void updateProduct(Product prod)
    {
        repo.save(prod);
    }

    public void deleteProduct(int id)
    {
        repo.deleteById(id);
    }








}
