package com.rapidvalue.master.mycustom.vo;

import java.util.List;

import com.rapidvalue.master.dao.annotations.Column;

public class MyLoginRespVO {

	@Column(name = "x_login_details_tab")
	private List<LoginDetailsTab> loginDetailsTab;

	@Column(name = "x_image")
	private byte[] employeeImage;

	@Column(name = "x_return_code")
	private String returnCode;

	@Column(name = "x_return_msg")
	private String returnMsg;

	public List<LoginDetailsTab> getLoginDetailsTab() {
		return loginDetailsTab;
	}

	public void setLoginDetailsTab(List<LoginDetailsTab> loginDetailsTab) {
		this.loginDetailsTab = loginDetailsTab;
	}

	public byte[] getEmployeeImage() {
		return employeeImage;
	}

	public void setEmployeeImage(byte[] employeeImage) {
		this.employeeImage = employeeImage;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

}
