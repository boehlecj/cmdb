package com.boehle.cmdb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.boehle.cmdb.model.Director;

public interface DirectorRepository extends CrudRepository<Director, Long>{
	List<Director> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
}
