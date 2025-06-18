package com.example.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Products;

public interface ProductsDAO extends CrudRepository<Products,Integer> {
}
