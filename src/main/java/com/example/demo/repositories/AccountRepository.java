package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Employee;
import com.example.demo.entities.User;

@Repository
public interface AccountRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT u FROM User u JOIN u.employee e WHERE e.email = ?1")
    public User login(String email);
}
