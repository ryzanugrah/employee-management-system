package com.example.employeemanagementsystem.audit.service.impl;

import com.example.employeemanagementsystem.audit.service.AuditService;
import com.example.employeemanagementsystem.exception.ResourceNotFoundException;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Object[]> getRevisionsByEntityId(Class<?> entity, Long id, boolean fetchChanges) throws ResourceNotFoundException {
        AuditQuery query;
        AuditReader reader = AuditReaderFactory.get(entityManager);

        if (fetchChanges) {
            query = reader.createQuery().forRevisionsOfEntityWithChanges(entity, true);
        } else {
            query = reader.createQuery().forRevisionsOfEntity(entity, true);
        }

        List<Object[]> revisions = query.add(AuditEntity.id().eq(id)).getResultList();

        if (revisions.isEmpty()) {
            throw new ResourceNotFoundException("No revisions found");
        } else {
            return revisions;
        }
    }
}
