package com.rapidvalue.master.mycustom.vo;

import java.util.List;

import com.rapidvalue.master.common.vo.BaseResponseVO;
import com.rapidvalue.master.dao.annotations.Column;

public class LoginRespVO extends BaseResponseVO {

	@Column(name = "x_person_dtls")
	private List<LoginDetailsTab> loginDetailsTab;

	@Column(name = "x_user_type")
	private String userType;

	public List<LoginDetailsTab> getLoginDetailsTab() {
		return loginDetailsTab;
	}

	public void setLoginDetailsTab(List<LoginDetailsTab> loginDetailsTab) {
		this.loginDetailsTab = loginDetailsTab;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
