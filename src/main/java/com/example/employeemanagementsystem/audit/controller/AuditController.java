package com.example.employeemanagementsystem.audit.controller;

import com.example.employeemanagementsystem.audit.service.AuditService;
import com.example.employeemanagementsystem.employee.entity.Employee;
import com.example.employeemanagementsystem.handler.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/audit")
public class AuditController {
    @Autowired
    AuditService auditService;

    @GetMapping(path = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Employee Revisions by Employee ID")
    public ResponseEntity<Object> getEmployeeRevisionsByEmployeeId(@PathVariable(name = "id") Long id,
                                                                   @RequestParam(defaultValue = "false",
                                                                           required = false) boolean fetchChanges) {

        List<Object[]> revisions = auditService.getRevisionsByEntityId(Employee.class, id, fetchChanges);
        return ResponseHandler.generateResponse(HttpStatus.OK,
                null,
                HttpStatus.OK.getReasonPhrase(),
                revisions);
    }
}
