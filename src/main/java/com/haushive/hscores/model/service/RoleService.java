package com.haushive.hscores.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
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
import com.haushive.hscores.model.domain.License;
import com.haushive.hscores.model.domain.Role;
import com.haushive.hscores.model.domain.Users;
import com.haushive.hscores.model.repository.LicenseRepository;

@Service
public class RoleService {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	public String createRole(Role role, License license) {
		String rawJson = "";
		String result = "";
		StringEntity stringEntity = null;
	    AuthenticationToken token = authService.getAuthenticationToken();
	    CloseableHttpResponse response = null;
		HttpPost post = new HttpPost("https://dev-42qz7bow.us.auth0.com/api/v2/roles");
		post.addHeader("content-type", "application/json");
		post.addHeader("Authorization", "Bearer " + token.getAccessToken());
	    ObjectMapper mapper = new ObjectMapper();
	    ObjectMapper resultsMapper = new ObjectMapper();
	    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
	    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    try {
			rawJson = mapper.writeValueAsString(role);
			stringEntity = new StringEntity(rawJson, ContentType.APPLICATION_FORM_URLENCODED);
			post.setEntity(stringEntity);
			response = HttpClientBuilder.create().build().execute(post);
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				role = resultsMapper.readValue(EntityUtils.toString(entity), Role.class);
				license.setRoleId(role.getId());
				return "success";
			}
			else {
				result = "error creating role: " + response.getStatusLine().getStatusCode();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Role> getRoles(AuthenticationToken token) {
		CloseableHttpResponse response = null;
		HttpGet get = new HttpGet("https://dev-42qz7bow.us.auth0.com/api/v2/roles");
		get.addHeader("content-type", "application/json");
		get.addHeader("Authorization", "Bearer " + token.getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		List<Role> roles = new ArrayList<>();
		try {
			response = HttpClientBuilder.create().build().execute(get);
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				roles = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Role>>(){});
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}
	
	public String getRoleIdFromRoles(List<Role> roles, String licenseKey) {
		String result = "";
		Role roleToReturn = new Role();
		roles.stream().forEach(role ->{
			if (role.getDescription().equals(licenseKey)) {
				roleToReturn.setId(role.getId());
			}
		});
		result = roleToReturn.getId();
		return result;
	}
	
	public void addUserToRole(String userId, AuthenticationToken token, String roleId) {
		String rawJson = "";
		String result = "";
		StringEntity stringEntity = null;
		CloseableHttpResponse response = null;
		Users users = new Users();
		List<String> userList = new ArrayList<>();
		userList.add(userId);
		users.setUsers(userList);
		HttpPost post = new HttpPost("https://dev-42qz7bow.us.auth0.com/api/v2/roles/" + roleId + "/users");
		post.addHeader("content-type", "application/json");
		post.addHeader("Authorization", "Bearer " + token.getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		ObjectMapper errorMapper = new ObjectMapper();
		try {
			rawJson = mapper.writeValueAsString(users);
			stringEntity = new StringEntity(rawJson, ContentType.APPLICATION_FORM_URLENCODED);
			post.setEntity(stringEntity);
			response = HttpClientBuilder.create().build().execute(post);
			if(response.getStatusLine().getStatusCode() == 200) {
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
//		return result;
	}
	
	public List<User> getUserInRole(AuthenticationToken token, String emailAddress) {
		CloseableHttpResponse response = null;
		Optional<License> license = licenseRepository.findByEmailAddress(emailAddress);
		String roleId = "";
		if(!license.isEmpty()) {
			roleId = license.get().getRoleId();
		}
		HttpGet get = new HttpGet("https://dev-42qz7bow.us.auth0.com/api/v2/roles/" + roleId + "/users");
		get.addHeader("content-type", "application/json");
		get.addHeader("Authorization", "Bearer " + token.getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		List<User> users = new ArrayList<>();
		try {
			response = HttpClientBuilder.create().build().execute(get);
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				users = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<User>>(){});
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public List<User> getUserInRoleWithId(AuthenticationToken token, String roleId) {
		CloseableHttpResponse response = null;
		HttpGet get = new HttpGet("https://dev-42qz7bow.us.auth0.com/api/v2/roles/" + roleId + "/users");
		get.addHeader("content-type", "application/json");
		get.addHeader("Authorization", "Bearer " + token.getAccessToken());
		ObjectMapper mapper = new ObjectMapper();
		List<User> users = new ArrayList<>();
		try {
			response = HttpClientBuilder.create().build().execute(get);
			if(response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				users = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<User>>(){});
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

}
