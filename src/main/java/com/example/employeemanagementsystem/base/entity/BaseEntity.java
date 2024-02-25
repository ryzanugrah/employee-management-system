package com.example.employeemanagementsystem.base.entity;

import com.example.employeemanagementsystem.audit.domain.AbstractAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@DynamicUpdate
@Data
public class BaseEntity extends AbstractAuditable<String, Long> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        this.isDeleted = false;
    }
}
