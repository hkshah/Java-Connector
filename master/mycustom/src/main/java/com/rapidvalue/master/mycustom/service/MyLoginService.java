package com.rapidvalue.master.mycustom.service;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.vo.MyLoginRespVO;
import com.rapidvalue.master.mycustom.vo.MyLoginReqVO;

public interface MyLoginService {

	MyLoginRespVO getEmployeeDetails(final MyLoginReqVO requestVO) throws MasterException;
}
