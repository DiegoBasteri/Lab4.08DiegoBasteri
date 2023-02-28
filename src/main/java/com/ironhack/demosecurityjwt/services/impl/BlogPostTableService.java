package com.ironhack.demosecurityjwt.services.impl;

import com.ironhack.demosecurityjwt.dtos.BlogPostTableDTO;
import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPostTable;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import com.ironhack.demosecurityjwt.repositories.BlogPostTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BlogPostTableService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BlogPostTableRepository blogPostTableRepository;


    //add post

    public BlogPostTable addPost (BlogPostTableDTO blogPostTableDTO){
       Author author = authorRepository.findById(blogPostTableDTO.getAuthor()).orElseThrow(()->
               new ResponseStatusException(HttpStatus.BAD_REQUEST,"AuthorId doesn't exist"));
       return blogPostTableRepository.save(new BlogPostTable(author,blogPostTableDTO.getTitle(),blogPostTableDTO.getPost()));
    }

    //Get Post

    public BlogPostTable getPostById (Integer postId){
        if (!blogPostTableRepository.findById(postId).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El post no existe");
        }
        return blogPostTableRepository.findById(postId).get();
    }

    //Get All Post
    public List<BlogPostTable> getAllPost (){
        return blogPostTableRepository.findAll();
    }


    // Delete Post

    public BlogPostTable deletePost(Integer id) {
        BlogPostTable blogPost = blogPostTableRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post with id " + id+ " not found"));

        if(blogPostTableRepository.findById(id).isPresent()){
            blogPostTableRepository.delete(blogPost);
        }
        return blogPost;
    }


    //Update Post

    public BlogPostTable updatePost(Integer id,BlogPostTableDTO postDTO){
        if (!blogPostTableRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El Post no existe");
        }
        BlogPostTable newPost = blogPostTableRepository.findById(id).get();
        newPost.setTitle(postDTO.getTitle());
        newPost.setPost(postDTO.getPost());
        newPost.setRIdAuthor(authorRepository.findById(postDTO.getAuthor()).get());

        return blogPostTableRepository.save(newPost);



    }
}


