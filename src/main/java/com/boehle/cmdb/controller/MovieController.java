package com.boehle.cmdb.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.boehle.cmdb.model.Movie;
import com.boehle.cmdb.service.MovieService;

@RestController
@RequestMapping("api/")
public class MovieController {
    @Autowired
    MovieService movieService;
    
    @GetMapping("time")
    private String getCurrentTime() {
    	Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
        String formatted = dateFormat.format(date);
        
        return formatted;
    }

    @GetMapping("movie/list")
    private List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("movie/{id}")
    private ResponseEntity<Movie> getMovie(@PathVariable("id") int id) {	
        return Optional
        		.ofNullable(movieService.getMovieById(id))
        		.map( movie -> ResponseEntity.ok().body(movie) )
        		.orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("movie/{id}")
    private void deleteMovie(@PathVariable("id") int id) {
        movieService.delete(id);
    }

    @PostMapping("movie")
    private long saveOrUpdateMovie(@RequestBody Movie movie) {
        movieService.saveOrUpdate(movie);
        return movie.getId();
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
	private ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity<String>("Invalid movie data. This movie already exists.", HttpStatus.BAD_REQUEST);
	}
}
