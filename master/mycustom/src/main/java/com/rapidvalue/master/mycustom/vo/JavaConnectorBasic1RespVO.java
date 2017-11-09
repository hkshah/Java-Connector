package com.rapidvalue.master.mycustom.vo;

import java.sql.Date;
import java.util.List;

import com.rapidvalue.master.dao.annotations.Column;

public class JavaConnectorBasic1RespVO extends BaseResponseVO {

    @Column(name = "x_address_details")
	private List<AddressDetails1> addressDetails1;
  
    @Column(name = "x_employee_image")
	private byte[] employeeImage;
	

	@Column(name = "x_created_date")
	private Date createdDate;


	public List<AddressDetails1> getAddressDetails1() {
		return addressDetails1;
	}


	public void setAddressDetails1(List<AddressDetails1> addressDetails1) {
		this.addressDetails1 = addressDetails1;
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
	
	
}
