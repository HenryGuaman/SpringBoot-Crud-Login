package com.example.demo.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Users;
import com.example.demo.service.IUsers;

@Service
public class UsersImple implements IUsers {

    @Transactional
    @Override
    public Users save(Users users) {
        return null;
    }
    @Transactional(readOnly = true)
    @Override
    public Users findById(Integer id) {
        return null;
    }
    @Transactional
    @Override
    public void delete(Users users) {

    }
}
