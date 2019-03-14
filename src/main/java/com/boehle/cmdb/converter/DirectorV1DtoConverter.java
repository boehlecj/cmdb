package com.boehle.cmdb.converter;

import org.springframework.stereotype.Component;

import com.boehle.cmdb.dto.DirectorDto;
import com.boehle.cmdb.dto.DirectorV1Dto;
import com.boehle.cmdb.model.Director;

@Component
public class DirectorV1DtoConverter implements DirectorDtoConverter {

	@Override
	public Director fromDto(DirectorDto dto) {
		Director director = new Director();
		director.setId(dto.getId());
		
		String[] directorName = ((DirectorV1Dto) dto).getName().split(" ");		
    	if(directorName.length != 2) {
    		directorName = new String[] {"Alan", "Smithee"};
    	}
    	
    	director.setFirstName(directorName[0]);
    	director.setLastName(directorName[1]);
    	
		return director;
	}

	@Override
	public DirectorV1Dto fromEntity(Director entity) {
		DirectorV1Dto dto = new DirectorV1Dto(entity.getId());
		StringBuilder sb = new StringBuilder(entity.getFirstName());
		sb.append(" ").append(entity.getLastName());
		dto.setName(sb.toString());
		
		return dto;
	}

}
