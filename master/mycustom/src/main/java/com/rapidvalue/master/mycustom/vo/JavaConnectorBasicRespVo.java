package com.rapidvalue.master.mycustom.vo;

import java.sql.Date;

import com.rapidvalue.master.dao.annotations.Column;

public class JavaConnectorBasicRespVo extends BaseResponseVO{
	
  	    public String getEmpComments() {
		return empComments;
	}

	public void setEmpComments(String empComments) {
		this.empComments = empComments;
	}

	public String getXmlOutput() {
		return xmlOutput;
	}

	public void setXmlOutput(String xmlOutput) {
		this.xmlOutput = xmlOutput;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

		@Column(name = "p_emp_comments")
		private String empComments;

		@Column(name = "x_xml_output")
		private String xmlOutput;
		
		@Column(name = "x_created_date")
		private Date createdDate;

}
