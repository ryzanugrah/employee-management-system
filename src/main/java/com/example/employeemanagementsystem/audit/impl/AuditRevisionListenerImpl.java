package com.example.employeemanagementsystem.audit.impl;

import com.example.employeemanagementsystem.audit.entity.AuditRevisionEntity;
import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;

import java.io.Serializable;
import java.util.Date;

public class AuditRevisionListenerImpl implements EntityTrackingRevisionListener {
    @Override
    public void entityChanged(Class entityClass,
                              String entityName,
                              Serializable entityId,
                              RevisionType revisionType,
                              Object revisionEntity) {
    }

    @Override
    public void newRevision(Object revisionEntity) {
        if (revisionEntity instanceof AuditRevisionEntity) {
            AuditRevisionEntity auditRevisionEntity = (AuditRevisionEntity) revisionEntity;
            auditRevisionEntity.setRevisionDate(new Date(auditRevisionEntity.getTimestamp()));
        }
    }
}
