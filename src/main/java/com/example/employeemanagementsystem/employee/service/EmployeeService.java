package com.example.employeemanagementsystem.employee.service;

import com.example.employeemanagementsystem.employee.dto.EmployeeDto;
import com.example.employeemanagementsystem.employee.dto.request.EmployeeRequestDto;
import com.example.employeemanagementsystem.employee.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Page<EmployeeDto> getEmployeePage(Integer page);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    Employee createEmployee(EmployeeRequestDto dto);

    Employee updateEmployee(Long id, EmployeeRequestDto dto);

    void deleteEmployee(Long id);
}
