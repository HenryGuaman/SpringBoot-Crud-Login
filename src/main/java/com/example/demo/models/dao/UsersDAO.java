package com.example.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.entity.Users;

@Repository
public interface UsersDAO extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}