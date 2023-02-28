package com.ironhack.demosecurityjwt.dtos;

import com.ironhack.demosecurityjwt.models.Author;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

@Data
public class BlogPostTableDTO {
@NotEmpty
@NotNull
private String title;

@NotEmpty
@NotNull
private String post;

@NotEmpty
@NotNull
private Integer author;



    public BlogPostTableDTO(String title, String post, Integer author) {
        this.title = title;
        this.post = post;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPostTableDTO that = (BlogPostTableDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(post, that.post) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, post, author);
    }
}
