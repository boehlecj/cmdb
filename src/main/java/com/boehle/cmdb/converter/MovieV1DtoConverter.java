package com.boehle.cmdb.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boehle.cmdb.dto.DirectorV1Dto;
import com.boehle.cmdb.dto.MovieDto;
import com.boehle.cmdb.dto.MovieV1Dto;
import com.boehle.cmdb.model.Director;
import com.boehle.cmdb.model.Movie;

@Component
public class MovieV1DtoConverter implements MovieDtoConverter {
	
	@Autowired
	DirectorV1DtoConverter directorV1Converter;

	@Override
	public Movie fromDto(MovieDto dto) {
		Movie movie = new Movie();
		if (Optional.ofNullable(((MovieV1Dto) dto).getId()).orElse(0l) != 0l) {
			movie.setId(dto.getId());
		}
		
		movie.setName(((MovieV1Dto) dto).getName());
		movie.setGenre(((MovieV1Dto) dto).getGenre());
		movie.setYear(((MovieV1Dto) dto).getYear());
		movie.setRating(((MovieV1Dto) dto).getRating());
		if (((MovieV1Dto) dto).getDirector() != null) {
			Director director = directorV1Converter.fromDto(((MovieV1Dto) dto).getDirector());
			movie.setDirector(director);
		}
		
		return movie;
	}

	@Override
	public MovieV1Dto fromEntity(Movie entity) {
		MovieV1Dto dto = new MovieV1Dto();
		
		dto.setName(entity.getName());
		dto.setGenre(entity.getGenre());
		dto.setYear(entity.getYear());
		dto.setRating(entity.getRating());
		
		if (entity.getDirector() != null) {
			DirectorV1Dto directorDto = directorV1Converter.fromEntity(entity.getDirector());
			dto.setDirector(directorDto);
		}
		
		return dto;
	}
	
}
