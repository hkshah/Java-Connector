package com.rapidvalue.master.mycustom.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.service.MyLoginService;
import com.rapidvalue.master.mycustom.vo.MyLoginReqVO;
import com.rapidvalue.master.mycustom.vo.MyLoginRespVO;

@Component
public class MyLoginDelegate {

	@Autowired
	private MyLoginService myLoginService;

	public MyLoginRespVO getMyLoginDetails(final MyLoginReqVO requestVO) throws MasterException {
		return myLoginService.getEmployeeDetails(requestVO);
	}
}
