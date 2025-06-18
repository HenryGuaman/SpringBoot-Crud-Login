package com.example.demo.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Sales;
import com.example.demo.service.ISales;


@Service
public class SaleImple implements ISales {
    
    @Transactional
    @Override
    public Sales save(Sales sales) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Sales findById(Integer id) {
        return null;
    }
}
