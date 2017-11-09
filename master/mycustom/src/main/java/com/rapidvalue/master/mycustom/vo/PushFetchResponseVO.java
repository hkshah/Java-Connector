package com.rapidvalue.master.mycustom.vo;

import java.util.List;

import com.rapidvalue.master.dao.annotations.Column;

public class PushFetchResponseVO {

	@Column(name = "x_push_not_fetch_tt")
	private List<PushFetchVO> pushNotFetchDtls;

	@Column(name = "x_return_code")
	private String returnCode;

	@Column(name = "x_return_msg")
	private String returnMsg;;

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

	public List<PushFetchVO> getPushNotFetchDtls() {
		return pushNotFetchDtls;
	}

	public void setPushNotFetchDtls(List<PushFetchVO> pushNotFetchDtls) {
		this.pushNotFetchDtls = pushNotFetchDtls;
	}

}