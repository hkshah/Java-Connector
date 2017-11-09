package com.rapidvalue.master.mycustom.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.delegate.MyLoginDelegate;
import com.rapidvalue.master.mycustom.vo.MyLoginReqVO;
import com.rapidvalue.master.mycustom.vo.MyLoginRespVO;

@RestController
@RequestMapping(value = "/mylogin")
public class MyLoginController {

	@Autowired
	private MyLoginDelegate myLoginDelegate;
	private static final Logger LOG = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/myLoginDetails", method = RequestMethod.POST, headers = "X-API-Version=v1")
	public MyLoginRespVO getEmployeeRespVO(@RequestBody final MyLoginReqVO requestVO) throws MasterException {
		LOG.debug("Entry:LoginController#getLoginDetails");
		return myLoginDelegate.getMyLoginDetails(requestVO);
	}
}
