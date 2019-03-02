package com.boehle.cmdb.service;

import org.springframework.data.repository.CrudRepository;

import com.boehle.cmdb.model.Movie;

/**
 * Since this is a pretty simple CRUD service for movies there is no need to get fancy with
 * the Data Access Layer, so I will just use the Spring Data CrudRepository interface.
 *
 */
public interface MovieRepository extends CrudRepository<Movie, Long>{}
