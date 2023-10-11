package com.example.studentmanagementsystem.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    @NotEmpty(message = "Student firstname should not be empty")
    private String firstName;
    @NotEmpty(message = "Student lastname should not be empty")
    private String lastName;
    @NotEmpty(message = "Student email should not be empty")
    @Email
    private String email;
}
