package com.boehle.cmdb.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.servlet.NoHandlerFoundException;

import com.boehle.cmdb.converter.DirectorV1DtoConverter;
import com.boehle.cmdb.converter.DirectorV2DtoConverter;
import com.boehle.cmdb.converter.MovieV1DtoConverter;
import com.boehle.cmdb.converter.MovieV2DtoConverter;
import com.boehle.cmdb.dto.DirectorDto;
import com.boehle.cmdb.dto.DirectorV1Dto;
import com.boehle.cmdb.dto.DirectorV2Dto;
import com.boehle.cmdb.dto.MovieDto;
import com.boehle.cmdb.dto.MovieV1Dto;
import com.boehle.cmdb.dto.MovieV2Dto;
import com.boehle.cmdb.model.Director;
import com.boehle.cmdb.model.Movie;
import com.boehle.cmdb.service.MovieService;
import com.boehle.cmdb.service.director.DirectorService;

@RestController
@RequestMapping("api/")
public class MovieController {

	@Autowired
	@Qualifier("movieserviceV2")
	MovieService movieService;

	@Autowired
	@Qualifier("directorservice")
	DirectorService directorService;

	// ------------------V1-------------------

	@Autowired
	MovieV1DtoConverter movieV1DtoConverter;
	@Autowired
	DirectorV1DtoConverter directorV1DtoConverter;

	@GetMapping({ "/time", "v1/time", "v2/time" })
	private String getCurrentTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		String formatted = dateFormat.format(date);

		return formatted;
	}

	@GetMapping("v1/movie/list")
	private List<MovieDto> getAllMoviesV1() {
		return movieV1DtoConverter.listFromEntities(movieService.getAllMovies());
	}

	@GetMapping("v1/movie/{id}")
	private ResponseEntity<MovieV1Dto> getMovieV1(@PathVariable("id") int id) {
		return Optional.ofNullable(movieService.getMovieById(id))
				.map(movie -> ResponseEntity.ok().body(movieV1DtoConverter.fromEntity(movie)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("v1/movie")
	private long saveOrUpdateMovieV1(@RequestBody MovieV1Dto dto) {
		Movie movie = movieV1DtoConverter.fromDto(dto);
		movie.setDirector(getDirectorV1(dto.getDirector()));
		return saveOrUpdateMovie(movie);
	}

	@DeleteMapping({ "movie/{id}", "v1/movie/{id}", "v2/movie/{id}" })
	private void deleteMovie(@PathVariable("id") int id) {
		movieService.delete(id);
	}

	private Director getDirectorV1(DirectorV1Dto dto) {
		// This can be a lot more sophisticated like looking for last name first, ect.
		// More focused on the service implementation at this point focusing on the
		String[] directorName = dto.getName().split(" ");
		if (directorName.length != 2) {
			directorName = new String[] { "Alan", "Smithee" };
		}

		Director director = directorService.getDirectorByName(directorName[0], directorName[1]);

		if (director == null) {
			director = new Director(directorName[0], directorName[1]);
			directorService.saveOrUpdate(director);
		}

		return director;
	}

	// ------------------V1 END---------------

	// ------------------Current V2-----------

	@Autowired
	MovieV2DtoConverter movieV2DtoConverter;
	@Autowired
	DirectorV2DtoConverter directorV2DtoConverter;

	@GetMapping({ "/movie/list", "v2/movie/list" })
	private List<MovieDto> getAllMovies() {
		return movieV2DtoConverter.listFromEntities(movieService.getAllMovies());
	}

	@GetMapping({ "movie/{id}", "v2/movie/{id}" })
	private ResponseEntity<MovieV2Dto> getMovie(@PathVariable("id") int id) {
		return Optional.ofNullable(movieService.getMovieById(id))
				.map(movie -> ResponseEntity.ok().body(movieV2DtoConverter.fromEntity(movie)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping({ "movie", "v2/movie" })
	private long saveOrUpdateMovie(@RequestBody MovieV2Dto dto) {
		Movie movie = movieV2DtoConverter.fromDto(dto);
		movie.setDirector(getDirectorV2(dto.getDirector()));
		return saveOrUpdateMovie(movie);
	}

	private long saveOrUpdateMovie(Movie movie) {
		movieService.saveOrUpdate(movie);
		return movie.getId();
	}

	@GetMapping({ "director/{id}", "v2/director/{id}" })
	private ResponseEntity<DirectorV2Dto> getDirector(@PathVariable("id") int id) {
		return Optional.ofNullable(directorService.getDirectorById(id))
				.map(director -> ResponseEntity.ok().body(directorV2DtoConverter.fromEntity(director)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping({ "director/list", "v2/director/list" })
	private List<DirectorDto> getAllDirectors() {
		return directorV2DtoConverter.listFromEntities(directorService.getAllDirectors());
	}

	@DeleteMapping({ "director/{id}", "v2/director/{id}" })
	private void deleteDirector(@PathVariable("id") int id) {
		directorService.delete(id);
	}

	@PostMapping({ "director", "v2/director" })
	private long saveOrUpdateDirector(@RequestBody DirectorV1Dto dto) {
		Director director = directorV2DtoConverter.fromDto(dto);
		directorService.saveOrUpdate(director);
		return director.getId();
	}

	private Director getDirectorV2(DirectorV2Dto directorDto) {
		Director director = directorService.getDirectorById(directorDto.getId());

		if (director == null) {
			director = new Director(directorDto.getFirstName(), directorDto.getLastName());
			directorService.saveOrUpdate(director);
		}

		return director;
	}

	// ------------------Current V2 END---------------

	// ------------------Error Handling---------------

	@ExceptionHandler(ConstraintViolationException.class)
	private ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity<String>("Invalid movie data. This movie already exists.", HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex) {
    	return "Url Mapping does not exist.";
    }

}
