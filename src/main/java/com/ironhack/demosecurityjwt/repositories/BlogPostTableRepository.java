package com.ironhack.demosecurityjwt.repositories;

import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPostTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostTableRepository extends JpaRepository <BlogPostTable,Integer> {


}
