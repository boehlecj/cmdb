package com.boehle.cmdb.converter;

import org.springframework.stereotype.Component;

import com.boehle.cmdb.dto.DirectorDto;
import com.boehle.cmdb.dto.DirectorV2Dto;
import com.boehle.cmdb.model.Director;

@Component
public class DirectorV2DtoConverter implements DirectorDtoConverter {
	@Override
	public Director fromDto(DirectorDto dto) {
		Director director = new Director();
		director.setId(dto.getId());
    	director.setFirstName(((DirectorV2Dto) dto).getFirstName());
    	director.setLastName(((DirectorV2Dto) dto).getLastName());
    	
		return director;
	}

	@Override
	public DirectorV2Dto fromEntity(Director entity) {
		DirectorV2Dto dto = new DirectorV2Dto(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		
		return dto;
	}
}
