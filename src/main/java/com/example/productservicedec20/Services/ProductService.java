package com.example.productservicedec20.Services;

import com.example.productservicedec20.Exceptions.ProductNotFoundException;
import com.example.productservicedec20.Models.Product;

import java.util.List;

public interface ProductService{

    public Product getSingleProduct(Long id) throws ProductNotFoundException;
    public List<Product> getAllProduct();
    public Product replaceProduct(Long id,Product product);

}
