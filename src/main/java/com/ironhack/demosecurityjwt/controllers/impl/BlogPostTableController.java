package com.ironhack.demosecurityjwt.controllers.impl;

import com.ironhack.demosecurityjwt.dtos.BlogPostTableDTO;
import com.ironhack.demosecurityjwt.models.BlogPostTable;
import com.ironhack.demosecurityjwt.services.impl.BlogPostTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogPostTableController {
    @Autowired
    BlogPostTableService blogPostTableService;


    @PostMapping("/addPost")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPostTable createNewPost(@RequestBody BlogPostTableDTO postDTO){
            return blogPostTableService.addPost(postDTO);
    }

    @GetMapping("/getPost/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogPostTable getPostById(@PathVariable Integer id){
        return blogPostTableService.getPostById(id);
    }

    @GetMapping("/getAllPost")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogPostTable> getAllPost (){
        return blogPostTableService.getAllPost();
    }

    @DeleteMapping("/deletePost/{postId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BlogPostTable deletePost (@PathVariable Integer postId){
        return blogPostTableService.deletePost(postId);
    }

    @PutMapping("/updatePost")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BlogPostTable updatedPost (@RequestParam Integer id,@RequestBody BlogPostTableDTO postDTO){
        return blogPostTableService.updatePost(id,postDTO);
    }

}
