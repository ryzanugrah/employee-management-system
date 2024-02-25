package com.example.employeemanagementsystem.employee.dto.request;

import com.example.employeemanagementsystem.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeRequestDto {
    @NotBlank(message = "First Name is required")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Size(max = 50)
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Size(min = 1, max = 15)
    @Pattern(regexp = "^\\d{1,15}$", message = "Invalid phone number")
    private String phoneNumber;

    private Gender gender;
}
