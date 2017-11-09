package com.rapidvalue.master.mycustom.vo;

import java.sql.Date;

import com.rapidvalue.master.dao.annotations.Column;

public class JavaConnectorBasicReqVo {
	
	
	
		@Column(name = "p_user_name")
		private String userName;

		@Column(name = "p_created_date")
		private Date createdDate;
		
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Integer getEmployeeAge() {
			return employeeAge;
		}

		public void setEmployeeAge(Integer employeeAge) {
			this.employeeAge = employeeAge;
		}

		public String getXmlData() {
			return xmlData;
		}

		public void setXmlData(String xmlData) {
			this.xmlData = xmlData;
		}

		public String getEmpComments() {
			return empComments;
		}

		public void setEmpComments(String empComments) {
			this.empComments = empComments;
		}

		@Column(name = "p_employee_age")
		private Integer employeeAge;

		@Column(name = "p_xml_data")
		private String xmlData;
		
		@Column(name = "p_emp_comments")
		private String empComments;
		
	
		
		

		
}
