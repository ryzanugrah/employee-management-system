package com.example.employeemanagementsystem.audit.service;

import com.example.employeemanagementsystem.exception.ResourceNotFoundException;

import java.util.List;

public interface AuditService {
    List<Object[]> getRevisionsByEntityId(Class<?> entity, Long id, boolean fetchChanges) throws ResourceNotFoundException;
}
