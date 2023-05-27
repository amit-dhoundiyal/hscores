package com.haushive.hscores.model.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haushive.hscores.WebApplicationException;
import com.haushive.hscores.model.domain.License;
import com.haushive.hscores.model.domain.Role;
import com.haushive.hscores.model.repository.LicenseRepository;

@Service
public class LicenseService {
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private SecurityService securityService;
	
//	Verifies a license key
	public boolean verifyLicenseKey(License license) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Optional<License> licenseToVerify = licenseRepository.findById(license.getLicenseKey());
		if (licenseToVerify.isEmpty()) {
			throw new WebApplicationException("Could not verify license key");
		}
		if (licenseToVerify.get().getLicenseKey().equals(license.getLicenseKey()) && licenseToVerify.get().getExpirationDate().after(now)) {
			return true;
		}
		else {
			throw new WebApplicationException("Your license has expired. Please renew your subscription.");
		}
	}
	
//	Generates license key by concatonating clinic id and location and hashing via SHA256
	private String generateLicenseKey(License license) {
		String licenseKey = "";
		String text = license.getClinic() + license.getLocation() + license.getAddDate().toString();
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
		licenseKey = Base64.getEncoder().encodeToString(hash);
		return licenseKey;
	}
	
//	Saves and returns license object
	public String returnLicense(License license) {
		Role role = new Role();
		String returnMessage = "";
		String pin = generatePin();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		license.setAddDate(now);
		license.setLocation(license.getLocation().toUpperCase());
		license.setClinic(license.getClinic().toUpperCase());
		license.setPin(securityService.hashString(pin));
		String licenseKey = generateLicenseKey(license);
		role.setName(license.getClinic() + " " + license.getLocation());
		role.setDescription(licenseKey);
		String result = roleService.createRole(role, license);
		if ("success".equals(result)) {
			license.setLicenseKey(licenseKey);
			licenseRepository.save(license);
			returnMessage = "CLINIC NAME: " + role.getName() + " LICENSE: " + role.getDescription() + " PIN: " + pin;
		} else {
			returnMessage = result;
		}
		return returnMessage;
	}
	
//	Generates random pin number
	public String generatePin() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String formatted = String.format("%05d", num); 
		return formatted;
	}
	
//	Verify Pin number
	public boolean verifyPin(String email, String pin) {
		Optional<License> license = licenseRepository.findByEmailAddress(email);
		String encodedPin = "";
		boolean result = false;
		if(!license.isEmpty()) {
			encodedPin = securityService.hashString(pin);
			result = securityService.verifyString(pin, encodedPin);
		}
		return result;
	}
	
}
