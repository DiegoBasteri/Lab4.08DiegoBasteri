package com.ironhack.demosecurityjwt;

import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPostTable;
import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.User;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import com.ironhack.demosecurityjwt.repositories.BlogPostTableRepository;
import com.ironhack.demosecurityjwt.services.impl.AuthorService;
import com.ironhack.demosecurityjwt.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class DemoSecurityJwtApplication {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BlogPostTableRepository blogPostTableRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoSecurityJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService, AuthorRepository authorRepository, BlogPostTableRepository blogPostTableRepository) {


        return args -> {


            //fill the Author table

            authorRepository.saveAll(Arrays.asList(

                    new Author("Diego"),
                    new Author("Anthony"),
                    new Author("Pere"),
                    new Author("Sebastian")
            ));

            blogPostTableRepository.saveAll(Arrays.asList(
                    new BlogPostTable(authorRepository.findById(1).get(),"Boost Your Productivity with 10 easy tips","Productivity - we all want it but it seems..."),
                    new BlogPostTable(authorRepository.findById(1).get(),"How to Focus","Do you ever sit down to work and find yourself …"),
                    new BlogPostTable(authorRepository.findById(3).get(),"Learn to Speed Read in 30 Days","Knowledge, not ability, is the great determiner of …")
            ));


            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));


            userService.saveUser(new User(null, "John Doe", "john", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "James Smith", "james", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Jane Carry", "jane", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Chris Anderson", "chris", "1234", new ArrayList<>()));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("james", "ROLE_ADMIN");
            userService.addRoleToUser("jane", "ROLE_USER");
            userService.addRoleToUser("chris", "ROLE_ADMIN");
            userService.addRoleToUser("chris", "ROLE_USER");

        };
    }


}
