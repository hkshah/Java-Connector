package com.rapidvalue.master.mycustom.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.dao.ProcedureDao;
import com.rapidvalue.master.dao.exception.DaoException;
import com.rapidvalue.master.mycustom.service.MyLoginService;
import com.rapidvalue.master.mycustom.util.Constant;
import com.rapidvalue.master.mycustom.vo.MyLoginRespVO;
import com.rapidvalue.master.mycustom.vo.MyLoginReqVO;

@Service
public class MyLoginServiceImpl implements MyLoginService {

	@Autowired
	private ProcedureDao procedureDao;

	private static final Logger LOG = Logger.getLogger(LoginServiceImpl.class);

	@Override
	public MyLoginRespVO getEmployeeDetails(MyLoginReqVO requestVO) throws MasterException {
		LOG.debug("Entry:LoginServiceImpl#getLoginDetails");
		final MyLoginRespVO responseVO = new MyLoginRespVO();
		try {
			procedureDao.execute(Constant.PROCEDURE_EMPLOYEE_DETAILS, requestVO, responseVO);
		} catch (DaoException e) {
			LOG.error("Get Employee Directory Procedure execution Failed::" + e);
			throw new MasterException(e.getCode(), e.getMessage(), e);
		}
		LOG.debug("Exit:LoginServiceImpl#getLoginDetails");
		return responseVO;
	}

}
