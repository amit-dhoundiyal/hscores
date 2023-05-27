package com.haushive.hscores.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.hscores.model.domain.SummaryScoreLatest;

@Repository
public interface LatestScoreRepository extends CrudRepository<SummaryScoreLatest, String>{
	public List<SummaryScoreLatest> findByEmailAddress(String emailAddress);
	public List<SummaryScoreLatest> findByEmailAddressIn(List<String> emailAddresss);
}
