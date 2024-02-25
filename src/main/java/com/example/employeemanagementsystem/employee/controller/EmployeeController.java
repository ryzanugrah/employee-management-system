package com.example.employeemanagementsystem.employee.controller;

import com.example.employeemanagementsystem.employee.dto.EmployeeDto;
import com.example.employeemanagementsystem.employee.dto.request.EmployeeRequestDto;
import com.example.employeemanagementsystem.employee.entity.Employee;
import com.example.employeemanagementsystem.employee.service.EmployeeService;
import com.example.employeemanagementsystem.handler.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/get-employee-page", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Employees by Page")
    public ResponseEntity<Object> getEmployeePage(@RequestParam(defaultValue = "1") Integer page) {
        Page<EmployeeDto> dto = employeeService.getEmployeePage(page);
        return ResponseHandler.generateResponse(HttpStatus.OK,
                null,
                HttpStatus.OK.getReasonPhrase(),
                dto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Employee by ID")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Long id) {
        EmployeeDto dto = employeeService.getEmployeeById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK,
                null,
                HttpStatus.OK.getReasonPhrase(),
                dto);
    }

    @GetMapping(value = "/get-all-employees", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get All Employees")
    public ResponseEntity<Object> getAllEmployees() {
        List<EmployeeDto> dto = employeeService.getAllEmployees();
        return ResponseHandler.generateResponse(HttpStatus.OK,
                null,
                HttpStatus.OK.getReasonPhrase(),
                dto);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create Employee")
    public ResponseEntity<Object> createEmployee(@RequestBody @Valid EmployeeRequestDto dto) {
        Employee entity = employeeService.createEmployee(dto);
        return ResponseHandler.generateResponse(HttpStatus.CREATED,
                null,
                HttpStatus.CREATED.getReasonPhrase(),
                entity);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update Employee")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") Long id,
                                                 @RequestBody @Valid EmployeeRequestDto dto) {
        Employee entity = employeeService.updateEmployee(id, dto);
        return ResponseHandler.generateResponse(HttpStatus.OK,
                null,
                HttpStatus.OK.getReasonPhrase(),
                entity);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete Employee")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseHandler.generateResponse(HttpStatus.OK,
                null,
                HttpStatus.OK.getReasonPhrase(),
                null);
    }
}
