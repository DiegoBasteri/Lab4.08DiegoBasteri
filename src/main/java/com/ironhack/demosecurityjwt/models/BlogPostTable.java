package com.ironhack.demosecurityjwt.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Optional;

@Entity
public class BlogPostTable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author RIdAuthor;

    private String title;

    private String post;

    public BlogPostTable(Author RIdAuthor, String title, String post) {
        this.RIdAuthor = RIdAuthor;
        this.title = title;
        this.post = post;
    }

    public BlogPostTable() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Author getRIdAuthor() {
        return RIdAuthor;
    }

    public void setRIdAuthor(Author RIdAuthor) {
        this.RIdAuthor = RIdAuthor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPostTable that = (BlogPostTable) o;
        return Objects.equals(id, that.id) && Objects.equals(RIdAuthor, that.RIdAuthor) && Objects.equals(title, that.title) && Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, RIdAuthor, title, post);
    }
}
