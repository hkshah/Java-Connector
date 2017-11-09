package com.rapidvalue.master.mycustom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.dao.ProcedureDao;
import com.rapidvalue.master.dao.exception.DaoException;
import com.rapidvalue.master.mycustom.service.JavaConnectorService;
import com.rapidvalue.master.mycustom.util.Constant;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic1ReqVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic1RespVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic2RespVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasicReqVo;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasicRespVo;
@Service
public class JavaConnectorBasicServiceImpl implements JavaConnectorService {

	@Autowired
	private ProcedureDao procedureDao;


	@Override
	public JavaConnectorBasicRespVo getJavaConnectorBasicDetails(JavaConnectorBasicReqVo requestVO) throws MasterException {
		final JavaConnectorBasicRespVo responseVO = new JavaConnectorBasicRespVo();
		try {
			procedureDao.execute(Constant.PROCEDURE_JAVA_CONNECTOR_PKG_JAVA_CONNECTOR_BASIC, requestVO, responseVO);
		} catch (DaoException e) {
			throw new MasterException(e.getCode(), e.getMessage(), e);
		}
		return responseVO;
	}
	
	
	@Override
	public JavaConnectorBasic2RespVO getJavaConnectorBasic2Details() throws MasterException {
		final JavaConnectorBasic2RespVO responseVO = new JavaConnectorBasic2RespVO();
		try {
			procedureDao.execute(Constant.PROCEDURE_JAVA_CONNECTOR_PKG_JAVA_CONNECTOR_BASIC2, responseVO);
		} catch (DaoException e) {
			throw new MasterException(e.getCode(), e.getMessage(), e);
		}
		return responseVO;
	}
	
	@Override
	public JavaConnectorBasic1RespVO getJavaConnectorBasic1Details(JavaConnectorBasic1ReqVO requestVO) throws MasterException {
		final JavaConnectorBasic1RespVO responseVO = new JavaConnectorBasic1RespVO();
		try {
			procedureDao.execute(Constant.PROCEDURE_JAVA_CONNECTOR_PKG_JAVA_CONNECTOR_BASIC3,requestVO, responseVO);
		} catch (DaoException e) {
			throw new MasterException(e.getCode(), e.getMessage(), e);
		}
		return responseVO;
	}


}
