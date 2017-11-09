package com.rapidvalue.master.mycustom.vo;

import com.rapidvalue.master.dao.annotations.Column;

public class BaseResponseVO {
	
	
    @Column(name = "x_return_code")
	private Integer returnCode;

	public Integer getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	@Column(name = "x_return_msg")
	private String returnMsg;

}
