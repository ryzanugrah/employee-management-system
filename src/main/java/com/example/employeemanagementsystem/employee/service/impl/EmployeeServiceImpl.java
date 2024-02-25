package com.example.employeemanagementsystem.employee.service.impl;

import com.example.employeemanagementsystem.employee.dto.EmployeeDto;
import com.example.employeemanagementsystem.employee.dto.request.EmployeeRequestDto;
import com.example.employeemanagementsystem.employee.entity.Employee;
import com.example.employeemanagementsystem.employee.repository.EmployeeRepository;
import com.example.employeemanagementsystem.employee.service.EmployeeService;
import com.example.employeemanagementsystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Integer ROWS_PER_PAGE = 10;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<EmployeeDto> getEmployeePage(Integer page) throws ResourceNotFoundException {
        Pageable pageable = PageRequest.of(page - 1, ROWS_PER_PAGE, Sort.by("id"));
        Page<Employee> entity = employeeRepository.findAll(pageable);

        if (entity.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }

        return new PageImpl<>(entity.stream()
                .map(employee -> new EmployeeDto(employee))
                .collect(Collectors.toList()), pageable, entity.getTotalElements());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) throws ResourceNotFoundException {
        Employee entity = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return new EmployeeDto(entity);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() throws ResourceNotFoundException {
        List<Employee> entities = employeeRepository.findAll();

        if (entities.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }

        return entities
                .stream()
                .map(employee -> new EmployeeDto(employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getPhoneNumber(),
                        employee.getGender(),
                        employee.getIsActivated(),
                        employee.getIsDeleted()))
                .collect(Collectors.toList());
    }

    @Override
    public Employee createEmployee(EmployeeRequestDto dto) {
        return employeeRepository.save(new Employee(dto));
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeRequestDto dto) {
        Employee entity = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setGender(dto.getGender());

        return employeeRepository.save(entity);
    }

    @Override
    public void deleteEmployee(Long id) throws ResourceNotFoundException {
        employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.deleteById(id);
    }
}
