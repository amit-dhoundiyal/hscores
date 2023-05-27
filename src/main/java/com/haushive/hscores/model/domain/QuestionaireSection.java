package com.haushive.hscores.model.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="questionaire_section")
public class QuestionaireSection {
	
	@Id
	private int sectionId;
	private int questionaireTypeRef;
	private String sectionName;
	private String sectionDesc;
	private boolean deleted;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp createdDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp updatedDate;
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getQuestionaireTypeRef() {
		return questionaireTypeRef;
	}
	public void setQuestionaireTypeRef(int questionaireTypeRef) {
		this.questionaireTypeRef = questionaireTypeRef;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionDesc() {
		return sectionDesc;
	}
	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
