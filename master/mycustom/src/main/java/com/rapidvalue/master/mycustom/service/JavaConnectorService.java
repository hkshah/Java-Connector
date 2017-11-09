package com.rapidvalue.master.mycustom.service;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic1ReqVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic1RespVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic2RespVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasicReqVo;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasicRespVo;

public interface JavaConnectorService {
	
	JavaConnectorBasicRespVo getJavaConnectorBasicDetails(final JavaConnectorBasicReqVo requestVO) throws MasterException;

	JavaConnectorBasic2RespVO getJavaConnectorBasic2Details() throws MasterException;
    
	JavaConnectorBasic1RespVO getJavaConnectorBasic1Details(final JavaConnectorBasic1ReqVO requestVO) throws MasterException;

}
