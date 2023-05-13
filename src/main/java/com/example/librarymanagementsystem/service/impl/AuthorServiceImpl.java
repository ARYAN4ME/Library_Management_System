package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.DTO.ResponseDto.AuthorResponseDto;
import com.example.librarymanagementsystem.entity.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public String addAuthor(Author author){
        authorRepository.save(author);
        return "added Author successfully";
    }

    @Override
    public AuthorResponseDto getByEmail(String email) {
        Author author = authorRepository.findByEmail(email);
//        lets make response Dto
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        return authorResponseDto;
    }
    @Override
    public String deleteById(int id){
        authorRepository.deleteById(id);
        return "Author deleted successfully";
    }

}
