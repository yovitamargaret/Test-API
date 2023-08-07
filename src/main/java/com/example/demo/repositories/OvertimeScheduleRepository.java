package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.OvertimeSchedule;


public interface OvertimeScheduleRepository extends JpaRepository<OvertimeSchedule,Integer> {
 
}
