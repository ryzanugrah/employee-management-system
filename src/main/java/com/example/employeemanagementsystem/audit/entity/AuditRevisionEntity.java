package com.example.employeemanagementsystem.audit.entity;

import com.example.employeemanagementsystem.audit.impl.AuditRevisionListenerImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "audit_log")
@RevisionEntity(AuditRevisionListenerImpl.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AuditRevisionEntity extends DefaultRevisionEntity {
    @ElementCollection
    @JoinTable(
            name = "audit_changes",
            joinColumns = @JoinColumn(name = "rev_id")
    )
    @ModifiedEntityNames
    private Set<String> modifiedEntity = new HashSet<>();

    private Date revisionDate;
}
