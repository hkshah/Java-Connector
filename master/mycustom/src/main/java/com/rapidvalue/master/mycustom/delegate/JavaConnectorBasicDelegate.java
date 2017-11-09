package com.rapidvalue.master.mycustom.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.service.JavaConnectorService;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic1ReqVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic1RespVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic2RespVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasicReqVo;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasicRespVo;
@Component
public class JavaConnectorBasicDelegate {
	@Autowired
	private JavaConnectorService javaConnectorService;
	//private static final Logger LOG = Logger.getLogger(LoginDelegate.class);

	public JavaConnectorBasicRespVo getJavaConnectorBasicDetails(final JavaConnectorBasicReqVo requestVO) throws MasterException {
		return javaConnectorService.getJavaConnectorBasicDetails(requestVO);
	}

	public JavaConnectorBasic2RespVO getJavaConnectorBasic2Details() throws MasterException {
		return javaConnectorService.getJavaConnectorBasic2Details();
	}
	
	public JavaConnectorBasic1RespVO getJavaConnectorBasic1Details(final JavaConnectorBasic1ReqVO requestVO) throws MasterException {
		return javaConnectorService.getJavaConnectorBasic1Details(requestVO);
	}
}
