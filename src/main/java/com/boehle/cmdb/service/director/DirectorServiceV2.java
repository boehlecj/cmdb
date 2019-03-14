package com.boehle.cmdb.service.director;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.boehle.cmdb.model.Director;
import com.boehle.cmdb.repository.DirectorRepository;

@Service
@Qualifier("directorservice")
public class DirectorServiceV2 implements DirectorService {
	
	@Autowired
    DirectorRepository directorRepository;

	@Override
	public List<Director> getAllDirectors() {
		List<Director> directors = new ArrayList<Director>();
        directorRepository.findAll().forEach(director -> directors.add(director));
        return directors;
	}

	@Override
	public Director getDirectorById(long id) {
		Optional<Director> director = directorRepository.findById(id);
    	if(director.isPresent()) {
    		return director.get();
    	} else {
    		return null;
    	}
	}

	@Override
	public void saveOrUpdate(Director director) {
		directorRepository.save(director);
		
	}

	@Override
	public void delete(long id) {
		try {
    		directorRepository.deleteById(id);
    	} catch (EmptyResultDataAccessException ex) {
            //Director id to delete doesn't exist. Data is idempotent, don't do anything
    	}
		
	}

	@Override
	public Director getDirectorByName(String firstName, String lastName) {
		List<Director> directors = directorRepository.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
		if (directors.size() > 0) {
			return directors.get(0);
		} else {
			return null;
		}
	}

}
