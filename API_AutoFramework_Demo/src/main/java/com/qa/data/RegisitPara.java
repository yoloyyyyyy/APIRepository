package com.qa.data;

public class RegisitPara {
	 private String region ;
	    private String mail;
	    private String password;
	    private String nickname;

	    public RegisitPara(String region, String mail, String password, String nickname) {
	        this.region = region;
	        this.mail = mail;
	        this.password = password;
	        this.nickname = nickname;
	    }

	    public String getRegion() {
	        return region;
	    }

	    public void setRegion(String region) {
	        this.region = region;
	    }

	    public String getMail() {
	        return mail;
	    }

	    public void setMail(String mail) {
	        this.mail = mail;
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
