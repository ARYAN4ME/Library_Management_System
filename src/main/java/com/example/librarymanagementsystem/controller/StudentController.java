package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.RequestDto.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.RequestDto.UpdateStudentMobRequestDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.StudentResponseDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.UpdateStuentMobNoResponseDto;
import com.example.librarymanagementsystem.exceptions.StudentNotFoundException;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){
        return studentService.addStudent(studentRequestDto);
    }
    @PutMapping("/update-mobile")
    public UpdateStuentMobNoResponseDto updateMobNo(@RequestBody UpdateStudentMobRequestDto updateStudentMobRequestDto)throws StudentNotFoundException {
        return studentService.updateMobNo(updateStudentMobRequestDto);
    }
    @GetMapping("/get-student")
    public StudentResponseDto getStudent(@RequestParam("id") int id){
        return studentService.getStudentById(id);
    }
    // delete a student by id
    // update the student by id
    // find all the students

}
