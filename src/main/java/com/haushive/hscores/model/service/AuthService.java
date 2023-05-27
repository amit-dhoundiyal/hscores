package com.haushive.hscores.model.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haushive.hscores.model.domain.AuthenticationToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class AuthService {
	
	public AuthenticationToken getAuthenticationToken() {
		String json = "";
		ObjectMapper objectMapper = new ObjectMapper();
		AuthenticationToken token = new AuthenticationToken();
		HttpResponse<String> response = null;
		try {
			response = Unirest.post("https://dev-42qz7bow.us.auth0.com/oauth/token")
					  .header("content-type", "application/json")
					  .body("{\"client_id\":\"1sse487RRZh9oLC9xLrFnM9vFutwqHzY\",\"client_secret\":\"Eb57cpziXOvoW9GbhLHgqM48WpLVFbrwK5kIp57Lom9hFuo1C2I2EuVH45pOu3qC\",\"audience\":\"https://dev-42qz7bow.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
					  .asString();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json = response.getBody();
		try {
			token = objectMapper.readValue(json, AuthenticationToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}

}
