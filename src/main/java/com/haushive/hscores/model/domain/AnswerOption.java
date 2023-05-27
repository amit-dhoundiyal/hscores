package com.haushive.hscores.model.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AnswerOption {
	@Id
	private int id;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDecription() {
		return description;
	}
	public void setDecription(String decription) {
		this.description = decription;
	}

	
}
