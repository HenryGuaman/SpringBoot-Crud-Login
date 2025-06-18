package com.example.demo.service;

import com.example.demo.models.entity.Sales;

public interface ISales {
    Sales save(Sales sales);
    Sales findById(Integer id);
}
