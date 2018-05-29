package com.zx.wx.http.entity;

import java.io.Serializable;

public class AccessToken implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String access_token;
	private int expires_in;
	public AccessToken() {
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
}

