package com.example.productservicedec20.Services;

import com.example.productservicedec20.Models.Product;

import java.util.List;

public interface ProductService{

    public Product getSingleProduct(Long id);
    public List<Product> getAllProduct();
    public Product replaceProduct(Long id,Product product);

}
