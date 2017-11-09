package com.rapidvalue.master.mycustom.vo;

import com.rapidvalue.master.dao.annotations.Column;

public class JavaConnectorBasic2RespVO extends BaseResponseVO {
    @Column(name = "x_emp_image")
	private byte[] empImage  ;

	public byte[] getEmpImage() {
		return empImage;
	}

	public void setEmpImage(byte[] empImage) {
		this.empImage = empImage;
	}
    
    
}
