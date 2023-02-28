package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPostTable;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

// get author by id
    public Author getAuthorById (Integer authorId){
        if (!authorRepository.findById(authorId).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El autor no existe");
        }
        return authorRepository.findById(authorId).get();
    }


    //add author
    public Author addAuthor (String newAuthor){
        return authorRepository.save(new Author(newAuthor));

    }

    //Delete Author

    public Author deleteAuthor(Integer id) {
        Author authorToDelete  = authorRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author with id " + id+ " not found"));

        if(authorRepository.findById(id).isPresent()){
            authorRepository.delete(authorToDelete);
        }
        return authorToDelete;
    }

}
