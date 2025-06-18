package com.example.demo.service;

import java.util.List;

import com.example.demo.models.entity.Products;

public interface IProducts  {

    Products save(Products products);
    Products findById(Integer id);
    void delete (Products products);
    List<Products>findAll();
}
