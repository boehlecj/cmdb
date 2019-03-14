package com.boehle.cmdb.service;

import java.util.List;

import com.boehle.cmdb.model.Movie;

public interface MovieService {

	List<Movie> getAllMovies();
	Movie getMovieById(long id);
	void saveOrUpdate(Movie movie);
	void delete(long id);

}