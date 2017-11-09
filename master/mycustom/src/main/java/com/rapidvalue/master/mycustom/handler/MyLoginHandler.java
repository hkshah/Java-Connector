package com.rapidvalue.master.mycustom.handler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.JdbcType;

import com.rapidvalue.master.common.util.Util;
import com.rapidvalue.master.dao.type.ArrayTypeHandler;
import com.rapidvalue.master.mycustom.vo.EmployeeDetails;

import oracle.sql.STRUCT;

public class MyLoginHandler extends ArrayTypeHandler<List<EmployeeDetails>> {

	private EmployeeDetails getEmployeeDetailsFromStruct(STRUCT struct) throws SQLException {

		EmployeeDetails employeeDetails = null;
		if (struct != null) {
			Object[] objArray = struct.getAttributes();
			employeeDetails = new EmployeeDetails();
			employeeDetails.setPerson_id((Util.convertBigDecimalToInt(objArray[0])));
			employeeDetails.setEmployee_number((String) objArray[1]);
			employeeDetails.setFull_name((String) objArray[2]);
			employeeDetails.setTitle((String) objArray[3]);
			employeeDetails.setBusiness_group_id((Util.convertBigDecimalToInt(objArray[4])));
			employeeDetails.setEmail_address((String) objArray[5]);
			employeeDetails.setWork_telephone((String) objArray[6]);
			employeeDetails.setOffice_number((String) objArray[7]);
			employeeDetails.setJob_id((Util.convertBigDecimalToInt(objArray[8])));
			employeeDetails.setJob_name((String) objArray[9]);
			employeeDetails.setPosition_id((Util.convertBigDecimalToInt(objArray[10])));
			employeeDetails.setPosition_name((String) objArray[11]);
			employeeDetails.setImage_id((Util.convertBigDecimalToInt(objArray[12])));
			employeeDetails.setSupervisor_name((String) objArray[13]);
		}
		return employeeDetails;
	}

	@Override
	public List<EmployeeDetails> getResult(ResultSet resultSet, String columnName) throws SQLException {
		return null;
	}

	@Override
	public List<EmployeeDetails> getResult(ResultSet resultSet, int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public List<EmployeeDetails> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
		Array array = cs.getArray(columnIndex);
		if (array != null) {
			employeeDetailsList = new ArrayList<>();
			Object[] structArray = (Object[]) cs.getArray(columnIndex).getArray();
			for (Object structObj : structArray) {
				EmployeeDetails employeeDetails = getEmployeeDetailsFromStruct((STRUCT) structObj);
				if (employeeDetails != null) {
					employeeDetailsList.add(employeeDetails);
				}
			}
		}
		return employeeDetailsList;
	}

	@Override
	public void setParameter(PreparedStatement ps, int columnIndex, List<EmployeeDetails> addressDetails1TabList,
			JdbcType jdbcType, String jdbcTypeName, String jdbcStruct) throws SQLException {
	}
}
