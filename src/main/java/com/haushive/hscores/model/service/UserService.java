package com.haushive.hscores.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.json.mgmt.users.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.haushive.hscores.model.domain.AuthError;
import com.haushive.hscores.model.domain.AuthenticationToken;
import com.haushive.hscores.model.domain.Role;
import com.haushive.hscores.model.domain.SummaryScore;
import com.haushive.hscores.model.domain.SummaryScoreLatest;
import com.haushive.hscores.model.repository.LatestScoreRepository;
import com.haushive.hscores.model.repository.SummaryScoreRepository;

@Service
public class UserService {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SummaryScoreRepository summaryScoreRepository;

	@Autowired 
	private LatestScoreRepository latestSummaryScoreRepository;
	
	@Autowired
	private SecurityService securityService;
	
	public List<SummaryScore> findScoresByUser(String emailAddress){
		List<SummaryScore> scores = new ArrayList<>();
//		String hashedEmail = securityService.hashString(emailAddress);
//		if(securityService.verifyString(emailAddress, hashedEmail)) {
//			try {
				scores = summaryScoreRepository.findByEmailAddressOrderByAddDateDesc(emailAddress);
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return scores;
	}

	public List<com.haushive.hscores.model.domain.User> findUserForAdminWithLatestScore(User admin) {
		if(!admin.getFamilyName().equals("admin-profile")) {
			return null;
			//throw new Exception("Do not have appropriate role");
		}
		// get all the users belonging to the admin
		List<User> usersOfAdmin = getRolesForAdmin(admin);

		// get latest scores for users
		List<String> mailIds = new ArrayList<>();
		usersOfAdmin.stream().forEach(usr -> mailIds.add(usr.getEmail()));
		List<SummaryScoreLatest> latestScores = latestSummaryScoreRepository.findByEmailAddressIn(mailIds);
		Map<String, SummaryScoreLatest> latestScoresMap = latestScores
		.stream()
		.collect(Collectors.toMap(SummaryScoreLatest::getEmailAddress, Function.identity()));

		// create user object to return with latest score
		List<com.haushive.hscores.model.domain.User> users = usersOfAdmin.stream()
		.map(usr -> new com.haushive.hscores.model.domain.User(usr))
		.collect(Collectors.toList());

		users.forEach(usr ->usr.setSummaryScoreLatest(latestScoresMap.get(usr.getEmail())));
		return users;
	}
	
	public String createUser(User user) {
		String rawJson = "";
		String result = "";
		StringEntity stringEntity = null;
	    AuthenticationToken token = authService.getAuthenticationToken();
	    CloseableHttpResponse response = null;
		HttpPost post = new HttpPost("https://dev-42qz7bow.us.auth0.com/api/v2/users");
		post.addHeader("content-type", "application/json");
		post.addHeader("Authorization", "Bearer " + token.getAccessToken());
//	    user.setConnection("basic-profile");
	    ObjectMapper mapper = new ObjectMapper();
	    ObjectMapper errorMapper = new ObjectMapper();
	    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
	    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    try {
			rawJson = mapper.writeValueAsString(user);
			stringEntity = new StringEntity(rawJson, ContentType.APPLICATION_FORM_URLENCODED);
			post.setEntity(stringEntity);
			response = HttpClientBuilder.create().build().execute(post);
			if(response.getStatusLine().getStatusCode() == 201) {
				result = "success";
				
			}
			else if (response.getStatusLine().getStatusCode() >= 400 && response.getStatusLine().getStatusCode() < 500) {
				HttpEntity entity = response.getEntity();
				AuthError error = errorMapper.readValue(EntityUtils.toString(entity), AuthError.class);
				result = error.getMessage();
			}
			else {
				result = "error: " + response.getStatusLine().getStatusCode();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String createUserFromLink(User user) {
		String rawJson = "";
		String result = "";
		StringEntity stringEntity = null;
	    AuthenticationToken token = authService.getAuthenticationToken();
	    CloseableHttpResponse response = null;
		HttpPost post = new HttpPost("https://dev-42qz7bow.us.auth0.com/api/v2/users");
		post.addHeader("content-type", "application/json");
		post.addHeader("Authorization", "Bearer " + token.getAccessToken());
//	    user.setConnection("basic-profile");
	    ObjectMapper mapper = new ObjectMapper();
	    ObjectMapper errorMapper = new ObjectMapper();
	    ObjectMapper userMapper = new ObjectMapper();
	    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
	    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    String license = user.getNickname();
	    user.setNickname(null);
	    try {
			rawJson = mapper.writeValueAsString(user);
			stringEntity = new StringEntity(rawJson, ContentType.APPLICATION_FORM_URLENCODED);
			post.setEntity(stringEntity);
			response = HttpClientBuilder.create().build().execute(post);
			if(response.getStatusLine().getStatusCode() == 201) {
				HttpEntity entity = response.getEntity();
				User userCreated = userMapper.readValue(EntityUtils.toString(entity), User.class);
				List<Role> roles = roleService.getRoles(token);
				String roleId = roleService.getRoleIdFromRoles(roles, license);
				roleService.addUserToRole(userCreated.getId(), token, roleId);
				result = "success";
				
			}
			else if (response.getStatusLine().getStatusCode() >= 400 && response.getStatusLine().getStatusCode() < 500) {
				HttpEntity entity = response.getEntity();
				AuthError error = errorMapper.readValue(EntityUtils.toString(entity), AuthError.class);
				result = error.getMessage();
			}
			else {
				result = "error: " + response.getStatusLine().getStatusCode();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String getRoleFromUser(String userId) {
		String result = "";
		String url = "https://dev-42qz7bow.us.auth0.com/api/v2/users/" + userId + "/roles";
		url = url.replace("|", "%7C");
	    AuthenticationToken token = authService.getAuthenticationToken();
	    CloseableHttpResponse response = null;
		HttpGet get = new HttpGet(url);
		get.addHeader("content-type", "application/json");
		get.addHeader("Authorization", "Bearer " + token.getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		List<Role> roles = new ArrayList<Role>();
	    try {
			response = HttpClientBuilder.create().build().execute(get);
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				roles = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Role>>(){});
				Role role = roles.get(0);
				result = role.getId();
			}
			else {
				result = "error: " + response.getStatusLine().getStatusCode();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<User> getRolesForAdmin(User user){
		List<User> usersToReturn = new ArrayList<User>();
		System.out.println("Filtered value: " + user.getEmail());
		if(user.getFamilyName().equals("admin-profile")) {
			String roleId = getRoleFromUser(user.getId());
			AuthenticationToken token = authService.getAuthenticationToken();
			usersToReturn = roleService.getUserInRoleWithId(token, roleId);
			// remove the login user from list
			usersToReturn = usersToReturn
			.stream()
			.filter(usr -> !usr.getEmail().equals(user.getEmail()))
			.peek(e -> System.out.println("Filtered value: " + e.getEmail()))
			.collect(Collectors.toList());
		}
		return usersToReturn;
	}

}
