package com.ironhack.demosecurityjwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.demosecurityjwt.dtos.BlogPostTableDTO;
import com.ironhack.demosecurityjwt.models.Author;
import com.ironhack.demosecurityjwt.models.BlogPostTable;
import com.ironhack.demosecurityjwt.repositories.AuthorRepository;
import com.ironhack.demosecurityjwt.repositories.BlogPostTableRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class AuthorControllerTesT {


    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BlogPostTableRepository blogPostTableRepository;

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private MvcResult mvcResult;


    Author author;
    BlogPostTable blogPostTable;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        author = authorRepository.save(new Author("Diego"));
        blogPostTable = blogPostTableRepository.save(new BlogPostTable(author, "title", "post1"));
    }

    @AfterEach
    void tearDown() {
        blogPostTableRepository.deleteAll();
        authorRepository.deleteAll();

    }

    //test create new author

    @Test
    public void testGet_Author_By_Id() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/getAuthor/" + author.getId()))
                .andExpect(status().isAccepted()).andReturn();
        //converting response in to a JSON to retrive the name for the test
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        assertEquals(jsonObject.get("name"), authorRepository.findById(author.getId()).get().getName());

    }


    //test get all post
    @Test
    public void getAllBlogPosts_successTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/getAllPost"))
                .andExpect(status().isOk()).andReturn();
        List<BlogPostTable> blogPostList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertEquals(blogPostTableRepository.findAll().size(), blogPostList.size());
    }

    //Test get PostAndAuthor
    @Test
    public void getPostAndAuthor_successTest() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/getPost/" + blogPostTable.getId()))
                .andExpect(status().isOk()).andReturn();
        BlogPostTable blogPost1 = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BlogPostTable.class);
        assertEquals(blogPost1.getRIdAuthor().getName(), blogPost1.getRIdAuthor().getName());
    }


    //Delete Post Succes
    @Test
    public void deletePost_successTest() throws Exception {
        mvcResult = mockMvc.perform(delete("/api/deletePost/" + blogPostTable.getId()))
                .andExpect(status().isAccepted()).andReturn();
        assertFalse(blogPostTableRepository.findById(blogPostTable.getId()).isPresent());
    }

    //Test Delete Post failure
    @Test
    public void deletePost_failureTest() throws Exception {
        mvcResult = mockMvc.perform(delete("/api/deletePost/" + blogPostTable.getId() + 0))
                .andExpect(status().isBadRequest()).andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains(" not found"));
    }

    //Test Create BlogPost
    @Test
    public void create_blogpost() throws Exception {
        BlogPostTableDTO blogpost = new BlogPostTableDTO("Test", "Post Test", 1);
        String body = objectMapper.writeValueAsString(blogpost);

        MvcResult mvcResult = mockMvc.perform(post("/api/addPost")
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Test"));

        assertEquals(blogpost.getAuthor(), blogPostTableRepository.findById(blogpost.getAuthor()).get().getId());

    }

    // Test Failure for addPost
    @Test
    public void addPost_failureTest() throws Exception {
        BlogPostTableDTO product = new BlogPostTableDTO("Test", "BlogPostTest", 100);
        String body = objectMapper.writeValueAsString(product);

        MvcResult mvcResult = mockMvc.perform(post("/api/addPost")
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResolvedException().getMessage().contains("AuthorId doesn't exist"));
    }

    //Delete Author Succes
    @Test
    public void deleteAuthor_successTest() throws Exception {
        mvcResult = mockMvc.perform(delete("/api/deleteAuthor/" + author.getId()))
                .andExpect(status().isAccepted()).andReturn();
        assertFalse(authorRepository.findById(author.getId()).isPresent());
    }

}


