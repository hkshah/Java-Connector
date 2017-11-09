package com.rapidvalue.master.mycustom.vo;

import java.sql.Date;
import java.util.List;

import com.rapidvalue.master.dao.annotations.Column;

public class JavaConnectorBasic1ReqVO {

	
       @Column(name = "p_user_name")
		private String userName;
      
        @Column(name = "p_employee_image")
		private byte[] employeeImage;
		
		@Column(name = "p_created_date")
		private Date createdDate;
		
		@Column(name = "p_employee_age")
		private Integer employeeAge;
		
		@Column(name = "p_address_details")
		private List<AddressDetails> addressDetails;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public byte[] getEmployeeImage() {
			return employeeImage;
		}

		public void setEmployeeImage(byte[] employeeImage) {
			this.employeeImage = employeeImage;
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

		public List<AddressDetails> getAddressDetails() {
			return addressDetails;
		}

		public void setAddressDetails(List<AddressDetails> addressDetails) {
			this.addressDetails = addressDetails;
		}
		
		
		
}
