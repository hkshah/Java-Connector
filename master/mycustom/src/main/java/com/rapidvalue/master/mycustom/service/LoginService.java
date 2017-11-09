package com.rapidvalue.master.mycustom.service;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.vo.LoginReqVO;
import com.rapidvalue.master.mycustom.vo.LoginRespVO;

public interface LoginService {

	LoginRespVO getLoginDetails(final LoginReqVO requestVO) throws MasterException;

	
}
