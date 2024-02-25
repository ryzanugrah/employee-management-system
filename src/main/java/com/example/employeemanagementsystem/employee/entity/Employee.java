package com.example.employeemanagementsystem.employee.entity;

import com.example.employeemanagementsystem.base.entity.BaseEntity;
import com.example.employeemanagementsystem.employee.dto.request.EmployeeRequestDto;
import com.example.employeemanagementsystem.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Employees",
        indexes = {
                @Index(name = "UQ__employees__email", columnList = "email", unique = true),
                @Index(name = "UQ__employees__phone_number", columnList = "phoneNumber", unique = true)
        })
@Audited(withModifiedFlag = true)
public class Employee extends BaseEntity {
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(length = 15, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Boolean isActivated;

    public Employee(EmployeeRequestDto dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.gender = dto.getGender();
        this.isActivated = false;
    }
}
