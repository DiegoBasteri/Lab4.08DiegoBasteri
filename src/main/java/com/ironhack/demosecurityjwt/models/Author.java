package com.ironhack.demosecurityjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idAuthor;

    private String name;

    @OneToMany(mappedBy = "RIdAuthor",fetch = FetchType.EAGER,cascade = CascadeType.ALL )
    @JsonIgnore
    private List<BlogPostTable> bigPostTableList;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    public Integer getId() {
        return idAuthor;
    }

    public void setId(Integer id) {
        this.idAuthor = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.idAuthor) && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
