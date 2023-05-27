package com.haushive.hscores.model.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Question {
	
	@Id
	private int questionId;
	private int questionaireType;
	
	@OneToOne
	@JoinColumn(name="questionaire_section")
	private QuestionaireSection questionaireSection;
	private int type;
	private String desc;
	private int next;
	private int previous;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "question_options",
	        joinColumns = @JoinColumn(name = "question_id"),
	        inverseJoinColumns = @JoinColumn(name = "option_id")
	)
	private Set<AnswerOption> options;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuestionaireType() {
		return questionaireType;
	}
	public void setQuestionaireType(int questionaireType) {
		this.questionaireType = questionaireType;
	}
	public QuestionaireSection getQuestionaireSection() {
		return questionaireSection;
	}
	public void setQuestionaireSection(QuestionaireSection questionaireSection) {
		this.questionaireSection = questionaireSection;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getPrevious() {
		return previous;
	}
	public void setPrevious(int previous) {
		this.previous = previous;
	}
	public Set<AnswerOption> getOptions() {
		return options;
	}

}
