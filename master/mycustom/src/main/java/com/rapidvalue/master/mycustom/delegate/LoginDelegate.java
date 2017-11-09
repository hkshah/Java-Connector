package com.rapidvalue.master.mycustom.delegate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.service.LoginService;
import com.rapidvalue.master.mycustom.vo.LoginReqVO;
import com.rapidvalue.master.mycustom.vo.LoginRespVO;

@Component
public class LoginDelegate {

	@Autowired
	private LoginService loginService;
	//private static final Logger LOG = Logger.getLogger(LoginDelegate.class);

	public LoginRespVO getLoginDetails(final LoginReqVO requestVO) throws MasterException {
		return loginService.getLoginDetails(requestVO);
	}
	
	
}