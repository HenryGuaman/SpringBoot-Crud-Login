package com.example.demo.service;

import com.example.demo.models.entity.Users;

public interface IUsers {

    Users save(Users users);
    Users findById(Integer id);
    void delete(Users users);
}
