package com.example.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Sales;

public interface SaleDAO extends CrudRepository<Sales,Integer> {
}
