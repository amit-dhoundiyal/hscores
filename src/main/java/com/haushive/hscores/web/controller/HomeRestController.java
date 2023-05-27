package com.haushive.hscores.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.json.mgmt.users.User;
import com.haushive.hscores.model.domain.AuthenticationToken;
import com.haushive.hscores.model.domain.Email;
import com.haushive.hscores.model.domain.License;
import com.haushive.hscores.model.domain.QuestionnaireInput;
import com.haushive.hscores.model.domain.SummaryScore;
import com.haushive.hscores.model.service.AuthService;
import com.haushive.hscores.model.service.LicenseService;
import com.haushive.hscores.model.service.QuestionnaireService;
import com.haushive.hscores.model.service.RoleService;
import com.haushive.hscores.model.service.UserService;

/*
 * This handles the rest calls 
 */

@CrossOrigin
@RestController
public class HomeRestController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private QuestionnaireService questionnaireService;
	
	@Autowired
	private LicenseService licenseService;
	
	@Autowired 
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/api/rest/hello")
	public Map<String, Object> hello(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("now", LocalDateTime.now());
		map.put("url", request.getRequestURL());
		return map;
	}
	
//	Get summary score by consuming a questionnaireInput object
	@RequestMapping("/api/summaryscore")
	public SummaryScore getSummaryScore(@RequestBody QuestionnaireInput input) {
		SummaryScore summaryScore = questionnaireService.summaryScore(input);
		return summaryScore;
	}

//	Get license verification
//	@RequestMapping("/api/license")
//	public boolean getLicenseVerification(@RequestBody License license) {
//		boolean isVerified = licenseService.verifyLicenseKey(license);
//		return isVerified;
//	}
	
	@RequestMapping("/api/license_key")
	public String generateLicenseKey(@RequestBody License license) {
		return licenseService.returnLicense(license);
	}
	
//	@RequestMapping("/api/auth")
//	public ResponseEntity<AuthenticationToken> getAuthorization() {
//		AuthenticationToken response = authService.getAuthenticationToken();
//		return ResponseEntity.ok().body(response);
//	}
	
	@RequestMapping("/api/user")
	public String createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@RequestMapping("/api/user/scores")
	public List<SummaryScore> findScoresByUser(@RequestBody Email email){
		return userService.findScoresByUser(email.getEmailAddress());
	}
	
	@RequestMapping("/create")
	public String createUserFromLink(@RequestBody User user) {
		return userService.createUserFromLink(user);
	}
	
	@RequestMapping("/api/verify")
	public boolean verifyPin(@RequestBody License license) {
		return licenseService.verifyPin(license.getEmailAddress(), license.getPin());
	}
	
//	@RequestMapping("/api/role/users")
//	public List<User> getUsersWithinRole(@RequestBody User user) {
//		return roleService.getUserInRole(authService.getAuthenticationToken(), user.getEmail());
//	}
	
	@RequestMapping("/api/role/users")
	public List<User> getUsersWithinRole(@RequestBody User user) {
		return userService.getRolesForAdmin(user);
	}

	@RequestMapping("/api/admin/scores")
	public List<com.haushive.hscores.model.domain.User> findUserScoresForAdmin(@RequestBody User user){
		return userService.findUserForAdminWithLatestScore(user);
	}
	
	@RequestMapping("/api/{questionaireType}/questions")
	public List<com.haushive.hscores.model.domain.Question> getQuestions(@PathVariable int questionaireType){
		return questionnaireService.getQuestions(questionaireType);
	}
}
