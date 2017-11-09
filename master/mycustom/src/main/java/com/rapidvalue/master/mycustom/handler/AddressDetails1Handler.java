package com.rapidvalue.master.mycustom.handler;

import java.io.IOException;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.JdbcType;

import com.rapidvalue.master.common.util.Util;
import com.rapidvalue.master.dao.type.ArrayTypeHandler;
import com.rapidvalue.master.mycustom.vo.AddressDetails1;

import oracle.sql.STRUCT;


public class AddressDetails1Handler extends ArrayTypeHandler<List<AddressDetails1>>{

	
	private AddressDetails1 getAddressDetails1romStruct(STRUCT struct) throws SQLException {

		AddressDetails1 addressDetails1 = null;
		if (struct != null) {
			Object[] objArray = struct.getAttributes();
			addressDetails1 = new AddressDetails1();
			addressDetails1.setAddressLine1((String) objArray[0]); 
			addressDetails1.setStartDate(Util.convertTimestampToDate((Timestamp) objArray[1]));
			addressDetails1.setAddressNumber((Util.convertBigDecimalToInt(objArray[2])));
			 try{
				 addressDetails1.setLocationimage((Util.convertClobToString((Clob) objArray[3])));
				 } catch (IOException e) {
				 e.printStackTrace();
				 }
		}
		return addressDetails1;
	}

	@Override
	public List<AddressDetails1> getResult(ResultSet resultSet, String columnName) throws SQLException {
	
		return null;
	}

	@Override
	public List<AddressDetails1> getResult(ResultSet resultSet, int columnIndex) throws SQLException {

		return null;
	}

	@Override
	public List<AddressDetails1> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		List<AddressDetails1> addressDetails1List = new ArrayList<>();
		Array array = cs.getArray(columnIndex);
		if (array != null) {
			addressDetails1List = new ArrayList<>();
			Object[] structArray = (Object[]) cs.getArray(columnIndex).getArray();
			for (Object structObj : structArray) {
				AddressDetails1 addressDetails1 = getAddressDetails1romStruct((STRUCT) structObj);
				if (addressDetails1 != null) {
					addressDetails1List.add(addressDetails1);
				}
			}
		}
		return addressDetails1List;
	}

	@Override
	public void setParameter(PreparedStatement ps, int columnIndex, List<AddressDetails1> addressDetails1TabList,
			JdbcType jdbcType, String jdbcTypeName, String jdbcStruct) throws SQLException {
	}
}


