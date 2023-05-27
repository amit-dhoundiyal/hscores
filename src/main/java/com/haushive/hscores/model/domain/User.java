package com.haushive.hscores.model.domain;

public class User {
	
	private String email;
	private String name;
	private String userId;
	private String connection;
	private String password;
	private String username;
	private String picture;
	private SummaryScoreLatest summaryScoreLatest;

	public User(){}

	public User(com.auth0.json.mgmt.users.User authUser) {
		this.email = authUser.getEmail();
		this.name = authUser.getName();
		this.username = authUser.getUsername();
		this.picture = authUser.getPicture();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getConnection() {
		return connection;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public SummaryScoreLatest getSummaryScoreLatest() {
		return summaryScoreLatest;
	}
	public void setSummaryScoreLatest(SummaryScoreLatest summaryScoreLatest) {
		this.summaryScoreLatest = summaryScoreLatest;
	}


	@Override
	public String toString() {
		return "{" +
			" email='" + getEmail() + "'" +
			", name='" + getName() + "'" +
			", userId='" + getUserId() + "'" +
			", connection='" + getConnection() + "'" +
			", password='" + getPassword() + "'" +
			", username='" + getUsername() + "'" +
			", summaryScoreLatest='" + getSummaryScoreLatest() + "'" +
			"}";
	}
	
}
