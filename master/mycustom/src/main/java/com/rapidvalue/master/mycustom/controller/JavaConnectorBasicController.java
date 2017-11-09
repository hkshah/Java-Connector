package com.rapidvalue.master.mycustom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rapidvalue.master.common.exception.MasterException;
import com.rapidvalue.master.mycustom.delegate.JavaConnectorBasicDelegate;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic1ReqVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic1RespVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasic2RespVO;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasicReqVo;
import com.rapidvalue.master.mycustom.vo.JavaConnectorBasicRespVo;

@RestController
@RequestMapping(value = "/javaconnectorbasic")
public class JavaConnectorBasicController {
	@Autowired
	private JavaConnectorBasicDelegate javaConnectorBasicDelegate;
	@RequestMapping(value = "/javaconnectorbasic1", method = RequestMethod.POST, headers = "X-API-Version=v1")
	public JavaConnectorBasicRespVo getJavaConnectorBasicDetails(@RequestBody final JavaConnectorBasicReqVo requestVO) throws MasterException {
		return javaConnectorBasicDelegate.getJavaConnectorBasicDetails(requestVO);
	}
	
	@RequestMapping(value = "/javaconnectorbasic2", method = RequestMethod.GET, headers = "X-API-Version=v1")
	public JavaConnectorBasic2RespVO getJavaConnectorBasic2Details() throws MasterException {
		return javaConnectorBasicDelegate.getJavaConnectorBasic2Details();
	}
	
	@RequestMapping(value = "/javaconnectorbasic3", method = RequestMethod.POST, headers = "X-API-Version=v1")
	public JavaConnectorBasic1RespVO getJavaConnectorBasic1Details(@RequestBody final JavaConnectorBasic1ReqVO requestVO) throws MasterException {
		return javaConnectorBasicDelegate.getJavaConnectorBasic1Details(requestVO);
	}

}
