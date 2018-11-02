package com.qa.data;

public class RegisitPara {
	private String region;
	private String email;
	private String password;
	private String nickname;

	public RegisitPara() {
		super();
	}

	public RegisitPara(String region, String email, String password, String nickname) {
		super();
		this.region = region;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
