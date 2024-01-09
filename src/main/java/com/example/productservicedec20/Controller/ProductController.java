package com.example.productservicedec20.Controller;

import com.example.productservicedec20.DTOs.ExceptionDTO;
import com.example.productservicedec20.Exceptions.ProductNotFoundException;
import com.example.productservicedec20.Models.Product;
import com.example.productservicedec20.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

@Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts()
    {
        ResponseEntity<List<Product>> response=
                new ResponseEntity<>(productService.getAllProduct(), HttpStatus.NOT_FOUND);
        return response;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
//        try {

            return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
//        } catch (ArithmeticException exception) {
//            ResponseEntity<Product> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            return response;
//        }
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        Product p= new Product();
        p.setTitle("A new Product");
        return p;
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new Product();
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id")Long id, @RequestBody Product product){
        return new Product();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteProduct(@PathVariable("id") Long id){
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Void> handleProductNotFoundException(){

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
