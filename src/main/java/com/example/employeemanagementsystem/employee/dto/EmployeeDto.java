package com.example.employeemanagementsystem.employee.dto;

import com.example.employeemanagementsystem.employee.entity.Employee;
import com.example.employeemanagementsystem.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private Boolean isActivated;
    private Boolean isDeleted;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        this.gender = employee.getGender();
        this.isActivated = employee.getIsActivated();
        this.isDeleted = employee.getIsDeleted();
    }
}
