package com.rapidvalue.master.mycustom.vo;

import com.rapidvalue.master.dao.annotations.Column;

public class MyLoginReqVO {

	@Column(name = "p_user_name")
	private String userName;

	@Column(name = "p_password")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
