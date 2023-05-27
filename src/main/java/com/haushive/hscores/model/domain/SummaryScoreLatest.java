package com.haushive.hscores.model.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="latest_score")
public class SummaryScoreLatest {
	
	@Id
	private String emailAddress;
	private Timestamp addDate;
	private Double physicalLimitationScore;
	private Double symptomFrequencyScore;
	private Double qualityOfLifeScore;
	private Double socialLimitationScore;
	private Double summaryScore;

	public SummaryScoreLatest(){}
	
	public SummaryScoreLatest(SummaryScore summaryScore){
		this.emailAddress = summaryScore.getEmailAddress();
		this.addDate = summaryScore.getAddDate();
		this.physicalLimitationScore = summaryScore.getPhysicalLimitationScore();
		this.qualityOfLifeScore = summaryScore.getQualityOfLifeScore();
		this.socialLimitationScore = summaryScore.getSocialLimitationScore();
		this.symptomFrequencyScore = summaryScore.getSymptomFrequencyScore();
		this.summaryScore = summaryScore.getSummaryScore();
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


	@Override
	public String toString() {
		return "{" +
			" emailAddress='" + getEmailAddress() + "'" +
			", addDate='" + getAddDate() + "'" +
			", physicalLimitationScore='" + getPhysicalLimitationScore() + "'" +
			", symptomFrequencyScore='" + getSymptomFrequencyScore() + "'" +
			", qualityOfLifeScore='" + getQualityOfLifeScore() + "'" +
			", socialLimitationScore='" + getSocialLimitationScore() + "'" +
			", summaryScore='" + getSummaryScore() + "'" +
			"}";
	}

}
