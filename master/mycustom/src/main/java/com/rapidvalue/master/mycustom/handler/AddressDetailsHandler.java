package com.rapidvalue.master.mycustom.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.JdbcType;

import com.rapidvalue.master.common.util.Util;
import com.rapidvalue.master.dao.type.ArrayTypeHandler;
import com.rapidvalue.master.mycustom.vo.AddressDetails;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.CLOB;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class AddressDetailsHandler extends ArrayTypeHandler<List<AddressDetails>> {

	@Override
	public void setParameter(PreparedStatement ps, int columnIndex, List<AddressDetails> addressDetailsList,
			JdbcType jdbcType, String jdbcTypeName, String jdbcStruct) throws SQLException {
		StructDescriptor structDescriptor = StructDescriptor.createDescriptor(jdbcStruct, ps.getConnection());
		STRUCT[] structs = new STRUCT[addressDetailsList.size()];
		for (int index = 0; index < addressDetailsList.size(); index++) {

			AddressDetails addressDetails = addressDetailsList.get(index);
			Object[] params = new Object[6];

			params[0] = addressDetails.getAddressLine1();
			params[1] = addressDetails.getStartDate();
			params[2] = Util.convertIntegerToBigDecimal(addressDetails.getAddressNumber());
			CLOB clob1 = (CLOB) ps.getConnection().createClob();
			clob1.setString(1, addressDetails.getLocationimage());
			params[3] = clob1;
			STRUCT struct = new STRUCT(structDescriptor, ps.getConnection(), params);
			structs[index] = struct;

			ArrayDescriptor desc = ArrayDescriptor.createDescriptor(jdbcTypeName, ps.getConnection());
			ARRAY oracleArray = new ARRAY(desc, ps.getConnection(), structs);
			ps.setArray(columnIndex, oracleArray);

		}
	}

	@Override
	public List<AddressDetails> getResult(CallableStatement cs, int columnIndex) throws SQLException {

		return null;
	}

	@Override
	public List<AddressDetails> getResult(ResultSet resultSet, String columnName) throws SQLException {

		return null;
	}

	@Override
	public List<AddressDetails> getResult(ResultSet resultSet, int columnIndex) throws SQLException {

		return null;
	}
}
