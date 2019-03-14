package com.boehle.cmdb.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boehle.cmdb.dto.DirectorV2Dto;
import com.boehle.cmdb.dto.MovieDto;
import com.boehle.cmdb.dto.MovieV2Dto;
import com.boehle.cmdb.model.Director;
import com.boehle.cmdb.model.Movie;

@Component
public class MovieV2DtoConverter implements MovieDtoConverter {
	
	@Autowired
	DirectorV2DtoConverter directorV2Converter;

	@Override
	public Movie fromDto(MovieDto dto) {
		Movie movie = new Movie();
		if (Optional.ofNullable(((MovieV2Dto) dto).getId()).orElse(0l) != 0l) {
			movie.setId(dto.getId());
		}
		
		movie.setName(((MovieV2Dto) dto).getName());
		movie.setGenre(((MovieV2Dto) dto).getGenre());
		movie.setYear(((MovieV2Dto) dto).getYear());
		movie.setRating(((MovieV2Dto) dto).getRating());
		movie.setDescription(((MovieV2Dto) dto).getDescription());
		
		if (((MovieV2Dto) dto).getDirector() != null) {
			Director director = directorV2Converter.fromDto(((MovieV2Dto) dto).getDirector());
			movie.setDirector(director);
		}
		
		return movie;
	}

	@Override
	public MovieV2Dto fromEntity(Movie entity) {
		MovieV2Dto dto = new MovieV2Dto();
		
		dto.setName(entity.getName());
		dto.setGenre(entity.getGenre());
		dto.setYear(entity.getYear());
		dto.setRating(entity.getRating());
		dto.setDescription(entity.getDescription());
		
		if (entity.getDirector() != null) {
			DirectorV2Dto directorDto = directorV2Converter.fromEntity(entity.getDirector());
			dto.setDirector(directorDto);
		}
		
		return dto;
	}
	
}

