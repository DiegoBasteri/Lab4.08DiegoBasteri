package com.ironhack.demosecurityjwt.controllers.impl;

import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPostTable;
import com.ironhack.demosecurityjwt.services.impl.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class AuthorController {
    @Autowired
    AuthorService authorService;


    @GetMapping("/getAuthor/{authorId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Author getAuthorById (@PathVariable Integer authorId){
        return authorService.getAuthorById(authorId);
    }

    @PostMapping("/addAuthor/{authorName}")
    @ResponseStatus(HttpStatus.CREATED)
    public  Author addAuthor(@PathVariable String authorName){
        return authorService.addAuthor(authorName);
    }

    @DeleteMapping("/deleteAuthor/{authorId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Author deleteAuthor(@PathVariable Integer authorId){
        return authorService.deleteAuthor(authorId);
    }


}
