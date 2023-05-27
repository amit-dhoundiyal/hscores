package com.haushive.hscores.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.hscores.model.domain.Email;

@Repository
public interface EmailRepository extends CrudRepository<Email, String>{

}
