package com.haushive.hscores.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.hscores.model.domain.SummaryScore;

@Repository
public interface SummaryScoreRepository extends CrudRepository<SummaryScore, String>{
	
	public List<SummaryScore> findByEmailAddressOrderByAddDateDesc(String emailAddress);

}
