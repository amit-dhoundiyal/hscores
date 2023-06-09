package com.haushive.hscores.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haushive.hscores.model.domain.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer>{
	
	public List<Question>findByQuestionaireType(Integer type);

}
