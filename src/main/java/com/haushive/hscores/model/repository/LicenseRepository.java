package com.haushive.hscores.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.haushive.hscores.model.domain.License;

public interface LicenseRepository extends CrudRepository<License, String>{
	
	public Optional<License> findByEmailAddress(String emailAddress);

}
