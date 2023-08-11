package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.User;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;
  
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean register(RegisterRequest registerRequest) {
        Employee employee = new Employee();
        employee.setEmail(registerRequest.getEmail());
        employee.setFullname(registerRequest.getName());
        employee.setPhone(null);
        employee.setJoindate(null);
        Department dept = new Department();
        dept.setDepartment_id(1);
        employee.setDepartment(dept);
        

        //insert employee

        Boolean resultEmployee = employeeService.Save(employee);
        if(resultEmployee){
            //inser user
            Integer employee_id = employeeService.findIdByEmail(registerRequest.getEmail());
            User user = new User();
            user.setUser_id(employee_id);
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

            Boolean resultUser = userService.Save(user);
            return resultUser;
        }

        return resultEmployee;
    }
    
}
