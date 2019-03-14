package com.boehle.cmdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.boehle.cmdb.model.Movie;
import com.boehle.cmdb.repository.MovieRepository;

@Service
@Qualifier("movieserviceV2")
public class MovieServiceV2 implements MovieService {
	Logger logger = LoggerFactory.getLogger(MovieServiceV2.class);
	
	@Autowired
    MovieRepository movieRepository;

    @Override
	public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }
    
    @Override
	public Movie getMovieById(long id) {
    	Optional<Movie> movie = movieRepository.findById(id);
    	if(movie.isPresent()) {
    		return movie.get();
    	} else {
    		return null;
    	}
    }

    @Override
	public void saveOrUpdate(Movie movie) {
    	
    	Objects.requireNonNull(movie.getName());;
    	
        movieRepository.save(movie);
        logger.debug("Movie title ('{}') was added or updated.", movie.getName());
    }
   
    @Override
    public void delete(long id) {
    	try {
    		movieRepository.deleteById(id);
    	} catch (EmptyResultDataAccessException ex) {
            //Movie id to delete doesn't exist. Data is idempotent, don't do anything except log
    		logger.debug("Movie id {} does not exist and cannot be deleted.", id);
        }
    }
}

