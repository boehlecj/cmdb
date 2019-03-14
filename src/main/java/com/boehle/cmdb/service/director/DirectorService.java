package com.boehle.cmdb.service.director;

import java.util.List;

import com.boehle.cmdb.model.Director;

public interface DirectorService {
	List<Director> getAllDirectors();
	Director getDirectorById(long id);
	Director getDirectorByName(String firstName, String lastName);
	void saveOrUpdate(Director movie);
	void delete(long id);

}
