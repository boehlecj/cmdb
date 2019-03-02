package com.boehle.cmdb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.boehle.cmdb.model.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

	@Autowired
    MovieService movieService;
	
	@Test
    public void getAllMoviesTest() throws Exception {
		List<Movie> movies = movieService.getAllMovies();
		assertTrue(movies.size() > 1);
	}
	
	@Test
    public void getMovieByIdTest() throws Exception {
		Movie movie = movieService.getMovieById(1);
		assertEquals( "Goodfellas", movie.getName());
	}
	
	@Test
	public void saveTest() throws Exception {
		Movie movie= new Movie("Iron Man", "Superhero", "2008", "John Faverau", 4);
		movieService.saveOrUpdate(movie);
		
		Movie addedMovie = movieService.getMovieById(movie.getId());
		assertEquals( movie.getName(), addedMovie.getName());
	}
	
	@Test(expected=NullPointerException.class)
	public void saveBadTest() throws Exception {
		Movie movie= new Movie(null, "Superhero", "2008", "John Faverau", 4);
		movieService.saveOrUpdate(movie);
	}
	
	@Test
	public void updateTest() throws Exception {
		Movie movie = movieService.getMovieById(3);
		double rating = movie.getRating();
		movie.setRating(4.5);
		movieService.saveOrUpdate(movie);
		
		Movie updatedMovie = movieService.getMovieById(3);
		assertFalse(updatedMovie.getRating() == rating);
			
	}
	
	@Test
	public void deleteTest() throws Exception {
		movieService.delete(1);
		
		Movie updatedMovie = movieService.getMovieById(1);
		assertTrue(updatedMovie == null);
			
	}

}
