package com.example.librarymanagementsystem.DTO.RequestDto;

import com.example.librarymanagementsystem.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
    private String name;
    private int age;
    private Department department;
    private String mobNo;
    private String email;


}
