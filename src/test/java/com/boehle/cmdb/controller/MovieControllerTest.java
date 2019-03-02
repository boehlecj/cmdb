package com.boehle.cmdb.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.boehle.cmdb.model.Movie;
import com.boehle.cmdb.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
	
	@Autowired
	ObjectMapper objectMapper;
	@Autowired 
	MockMvc mvc;
    @MockBean 
    MovieService movieService;
    
    @Test
    public void addMovieTest() throws Exception {
        Movie movie = new Movie(1, "Psycho", "horror", "1961", "Alfred Hitchcock", 5);
        String inputJson = objectMapper.writeValueAsString(movie);

        this.mvc.perform(post("/api/movie")
            .contentType(MediaType.APPLICATION_JSON)
            .content(inputJson))
            .andExpect(status().isOk());
    }
    
    @Test
    public void getAllMoviesTest() throws Exception {
    	Movie movie1 = new Movie(1, "Psycho", "horror", "1961", "Alfred Hitchcock", 5);
    	Movie movie2 = new Movie(2, "Casino", "crime", "2000", "Martin Scorses", 5);
    	
    	when(movieService.getAllMovies()).thenReturn(Arrays.asList(movie1, movie2));
    	
    	this.mvc.perform(get("/api/movie/list"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Psycho"))
        .andExpect(jsonPath("$[1].name").value("Casino"));
    }
    
    @Test
    public void getMovieTest() throws Exception {
    	Movie movie = new Movie(1, "Psycho", "horror", "1961", "Alfred Hitchcock", 5);	
    	when(movieService.getMovieById(1)).thenReturn(movie);
    	
    	this.mvc.perform(get("/api/movie/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name").value("Psycho"));
    }
    
    @Test
    public void getMovieNotFoundTest() throws Exception {    	
    	this.mvc.perform(get("/api/movie/2"))
        .andExpect(status().isNotFound());
    }
    
    @Test
    public void deleteMovieTest() throws Exception {    	
    	this.mvc.perform(delete("/api/movie/2"))
        .andExpect(status().isOk());
    }
    
    @Test
    public void getCurrentTime() throws Exception {
        this.mvc.perform(get("/api/time"))
            .andExpect(status().isOk());
    }

}
