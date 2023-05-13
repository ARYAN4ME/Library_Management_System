package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.ResponseDto.AuthorResponseDto;
import com.example.librarymanagementsystem.entity.Author;
import com.example.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
        public String addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }
    @GetMapping("/get-by-email")
    public AuthorResponseDto getAuthorByEmail(@RequestParam("email") String email){
        return authorService.getByEmail(email);
    }
    @PutMapping("/deleteById")
    public String deletePutId(@RequestParam("id") int id){
        return null;
    }
    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("id") int id){
        return authorService.deleteById(id);

    }
    // delete a student by id

    // update the student by id

    // find a student by id

    // find all the students

    // get all the authors of a particular age
}
