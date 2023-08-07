package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.OvertimeStatus;


public interface OvertimeStatusRepository extends JpaRepository<OvertimeStatus,Integer> {
 
}
