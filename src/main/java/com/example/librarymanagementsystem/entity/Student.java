package com.example.librarymanagementsystem.entity;

import com.example.librarymanagementsystem.enums.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Department department;
    private String mobNo;
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    Card card;
}
