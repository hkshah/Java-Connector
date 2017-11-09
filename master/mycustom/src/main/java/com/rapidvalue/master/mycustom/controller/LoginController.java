package com.rapidvalue.master.mycustom.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.delegate.LoginDelegate;
import com.rapidvalue.master.mycustom.vo.LoginReqVO;
import com.rapidvalue.master.mycustom.vo.LoginRespVO;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private LoginDelegate loginDelegate;
	private static final Logger LOG = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/loginDetails", method = RequestMethod.POST, headers = "X-API-Version=v1")
	public LoginRespVO getEmpDirRespVO(@RequestBody final LoginReqVO requestVO) throws MasterException {
		LOG.debug("Entry:LoginController#getLoginDetails");
		return loginDelegate.getLoginDetails(requestVO);
	}
	
	
}
