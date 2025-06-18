package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.models.dao.ProductsDAO;
import com.example.demo.models.entity.Products;
import com.example.demo.service.IProducts;

import java.util.List;

@Service
public class PeroductsImpl implements IProducts {
    @Autowired
    private ProductsDAO productsDAO;

    @Transactional
    @Override
    public Products save(Products products){
        return productsDAO.save(products);
    }
    @Transactional(readOnly = true)
    @Override
    public Products findById(Integer id) {
        return productsDAO.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Sin existencias del producto o no disponible  " + id));
    }
    public List<Products> findAll() {
        return (List<Products>) productsDAO.findAll();
    }
    @Transactional
    @Override
    public void delete(Products products) {
        productsDAO.delete(products);
    }
}
