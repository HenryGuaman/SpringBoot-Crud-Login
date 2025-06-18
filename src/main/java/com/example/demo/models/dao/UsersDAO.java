package com.example.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Users;

public interface UsersDAO extends CrudRepository<Users,Integer> {
}
