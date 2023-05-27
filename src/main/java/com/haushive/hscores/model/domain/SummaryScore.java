package com.haushive.hscores.model.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="summary_score")
public class SummaryScore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String emailAddress;
	private Timestamp addDate;
	private Double physicalLimitationScore;
	private Double symptomFrequencyScore;
	private Double qualityOfLifeScore;
	private Double socialLimitationScore;
	private Double summaryScore;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPhysicalLimitationScore() {
		return physicalLimitationScore;
	}
	public void setPhysicalLimitationScore(Double physicalLimitationScore) {
		this.physicalLimitationScore = physicalLimitationScore;
	}
	public Double getSymptomFrequencyScore() {
		return symptomFrequencyScore;
	}
	public void setSymptomFrequencyScore(Double symptomFrequencyScore) {
		this.symptomFrequencyScore = symptomFrequencyScore;
	}
	public Double getQualityOfLifeScore() {
		return qualityOfLifeScore;
	}
	public void setQualityOfLifeScore(Double qualityOfLifeScore) {
		this.qualityOfLifeScore = qualityOfLifeScore;
	}
	public Double getSocialLimitationScore() {
		return socialLimitationScore;
	}
	public void setSocialLimitationScore(Double socialLimitationScore) {
		this.socialLimitationScore = socialLimitationScore;
	}
	public Double getSummaryScore() {
		return summaryScore;
	}
	public void setSummaryScore(Double summaryScore) {
		this.summaryScore = summaryScore;
	}
	public Timestamp getAddDate() {
		return addDate;
	}
	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
