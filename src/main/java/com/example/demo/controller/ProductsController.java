package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.entity.Products;
import com.example.demo.service.IProducts;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ProductsController {
    @Autowired
    private IProducts productsService;

    @PostMapping("/product")
    public Products create (@RequestBody Products products){
        return productsService.save(products);
    }
    @PutMapping("/products")
    public Products update(@RequestBody Products products){
        return  productsService.save(products);
    }
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") Integer id){
        Products productsDelete = productsService.findById(id);
        productsService.delete(productsDelete);
    }
    @GetMapping("/products/{id}")
    public Products showById(@PathVariable("id") Integer id){
        return  productsService.findById(id);
    }
    @GetMapping("/products")
    public List<Products> findAll() {
        return productsService.findAll();
    }

}
