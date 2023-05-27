package com.haushive.hscores.model.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haushive.hscores.model.domain.Email;
import com.haushive.hscores.model.domain.Question;
import com.haushive.hscores.model.domain.QuestionnaireInput;
import com.haushive.hscores.model.domain.SummaryScore;
import com.haushive.hscores.model.domain.SummaryScoreLatest;
import com.haushive.hscores.model.repository.EmailRepository;
import com.haushive.hscores.model.repository.LatestScoreRepository;
import com.haushive.hscores.model.repository.QuestionRepository;
import com.haushive.hscores.model.repository.SummaryScoreRepository;

/*
 * Handles all logic for the 5 parts of an H-Score
 */

@Service
public class QuestionnaireService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired 
	private SummaryScoreRepository summaryScoreRepository;

	@Autowired 
	private LatestScoreRepository latestSummaryScoreRepository;
	
	@Autowired 
	private SecurityService securityService;
	
	@Autowired
	private QuestionRepository questionRepository;
	
//	Save score
	public void saveScore(QuestionnaireInput input, SummaryScore summaryScore) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		summaryScore.setAddDate(now);
		summaryScore.setEmailAddress(input.getEmailAddress());
		SummaryScoreLatest summaryScoreLatest = new SummaryScoreLatest(summaryScore);
		summaryScoreRepository.save(summaryScore);
		latestSummaryScoreRepository.save(summaryScoreLatest);
	}
	
//	Save email 
	public void saveEmail(QuestionnaireInput input) {
		Email email = new Email();
		email.setEmailAddress(input.getEmailAddress());
		email.setLicenseKey(input.getLicenseKey());
		emailRepository.save(email);
	}
	
//	KCCQ12-PL score
	public double physicalLimitationScore(double question1A, double question1B, double question1C) {
		double average = 100 * (((question1A + question1B + question1C) / 3.0) - 1.0) / 4.0;
		return average;
	}
	
//	KCCQ12-SF score
	public Double symptomFrequencyScore(double question2, double question3, double question4, double question5) {
		double q2 = 100.0 * (question2 - 1.0) / 4.0;
		double q3 = 100.0 * (question3 - 1.0) / 6.0;
		double q4 = 100.0 * (question4 - 1.0) / 6.0;
		double q5 = 100.0 * (question5 - 1.0) / 4.0;
		double average = (q2 + q3 + q4 + q5) / 4.0;
		return average;
	}
	
//	KCCQ12-QL score
	public Double qualityOfLifeScore(double question6, double question7) {
		double average = 100.0 * (((question6 + question7) / 2.0) - 1.0) / 4.0;
		return average;
	}
	
//	KCCQ12-SL score
	public Double socialLimitationScore(double question8A, double question8B, double question8C) {
		double average = 100 * (((question8A + question8B + question8C) / 3.0) - 1.0) / 4.0;
		return average;
	}
	
//	KCCQ12 score
	public Double summaryScore(double physicalLimitation, double symptomFrequency, double qualityOfLife, double socialLimitation) {
		DecimalFormat df = new DecimalFormat("#.######");
		double average = (physicalLimitation + symptomFrequency + qualityOfLife + socialLimitation) / 4.0;
		return Double.valueOf(df.format(average));
	}
	
//	Set summaryScore
	public void setSummaryScore(SummaryScore summaryScore, double physicalLimitation, double symptomFrequency, double qualityOfLife, double socialLimitation) {
		DecimalFormat df = new DecimalFormat("#.######");
		summaryScore.setPhysicalLimitationScore(Double.valueOf(df.format(physicalLimitation))); 
		summaryScore.setSymptomFrequencyScore(Double.valueOf(df.format(symptomFrequency)));
		summaryScore.setQualityOfLifeScore(Double.valueOf(df.format(qualityOfLife)));
		summaryScore.setSocialLimitationScore(Double.valueOf(df.format(socialLimitation)));
		summaryScore.setSummaryScore(summaryScore(physicalLimitation, symptomFrequency, qualityOfLife, socialLimitation));
	}
	
//	Creates Summary Score object
	public SummaryScore summaryScore(QuestionnaireInput input){
		String encodedEmail = "";
		SummaryScore summaryScore = new SummaryScore();
		Double physicalLimitation = physicalLimitationScore(input.getQuestion1a(), input.getQuestion1b(), input.getQuestion1c());
		Double symptomFrequency = symptomFrequencyScore(input.getQuestion2(), input.getQuestion3(), input.getQuestion4(), input.getQuestion5());
		Double qualityOfLife = qualityOfLifeScore(input.getQuestion6(), input.getQuestion7());
		Double socialLimitation = socialLimitationScore(input.getQuestion8a(), input.getQuestion8b(), input.getQuestion8c());
		setSummaryScore(summaryScore, physicalLimitation, symptomFrequency, qualityOfLife, socialLimitation);
//		encodedEmail = securityService.hashString(input.getEmailAddress());
//		input.setEmailAddress(encodedEmail);
		System.out.println(encodedEmail);
		saveScore(input, summaryScore);
//		saveEmail(input);
		return summaryScore;
	}
	
	public List<Question> getQuestions(int type) {
		return questionRepository.findByQuestionaireType(type);
	}
}
