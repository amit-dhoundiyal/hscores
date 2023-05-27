package com.haushive.hscores.model.service;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
	
	public String hashString(String stringToHash) {
		String hashedString = "";
		Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
		hashedString = encoder.encode(stringToHash);
		return hashedString;
	}
	
	public boolean verifyString(String stringToVerify, String hashedString) {
		boolean verified = false;
		Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
		if (encoder.matches(stringToVerify, hashedString)) {
			verified = true;
		}
		return verified;
	}

}
