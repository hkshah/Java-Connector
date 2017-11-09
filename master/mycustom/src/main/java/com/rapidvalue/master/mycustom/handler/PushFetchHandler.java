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
import com.rapidvalue.master.mycustom.vo.PushFetchVO;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class PushFetchHandler extends ArrayTypeHandler<List<PushFetchVO>> {

	private PushFetchVO getExpenseHeaderFromStruct(STRUCT struct) throws SQLException {

		PushFetchVO pushFetch = null;
		if (struct != null) {
			Object[] objArray = struct.getAttributes();
			pushFetch = new PushFetchVO();
			pushFetch.setSlId((Util.convertBigDecimalToInt(objArray[0])));
			pushFetch.setMessageName((String) objArray[1]);
			pushFetch.setSubject((String) objArray[2]);
			pushFetch.setFromRole((String) objArray[3]);
			pushFetch.setToRole((String) objArray[4]);
			pushFetch.setStatus((String) objArray[5]);
			pushFetch.setMoreInfoRole((String) objArray[6]);
			pushFetch.setItemKey((String) objArray[7]);
			pushFetch.setNotificationId((Util.convertBigDecimalToInt(objArray[8])));
			pushFetch.setNavigationPath((String) objArray[9]);
		}
		return pushFetch;
	}

	@Override
	public List<PushFetchVO> getResult(ResultSet resultSet, String columnName) throws SQLException {
		List<PushFetchVO> pushFetchList = new ArrayList<>();
		while (resultSet != null && resultSet.next()) {
			PushFetchVO pushFetch = getExpenseHeaderFromStruct((STRUCT) resultSet.getObject(columnName));
			if (pushFetch != null) {
				pushFetchList.add(pushFetch);
			}
		}
		return pushFetchList;
	}

	@Override
	public List<PushFetchVO> getResult(ResultSet resultSet, int columnIndex) throws SQLException {
		List<PushFetchVO> pushFetchList = new ArrayList<>();
		while (resultSet != null && resultSet.next()) {
			PushFetchVO pushFetch = getExpenseHeaderFromStruct((STRUCT) resultSet.getObject(columnIndex));
			if (pushFetch != null) {
				pushFetchList.add(pushFetch);
			}
		}
		return pushFetchList;
	}

	@Override
	public List<PushFetchVO> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		List<PushFetchVO> pushFetchList = new ArrayList<>();
		Array array = cs.getArray(columnIndex);
		if (array != null) {
			pushFetchList = new ArrayList<>();
			Object[] structArray = (Object[]) cs.getArray(columnIndex).getArray();
			for (Object structObj : structArray) {
				PushFetchVO pushFetch = getExpenseHeaderFromStruct((STRUCT) structObj);
				if (pushFetch != null) {
					pushFetchList.add(pushFetch);
				}
			}
		}
		return pushFetchList;
	}

	@Override
	public void setParameter(PreparedStatement ps, int columnIndex, List<PushFetchVO> pushFetchList, JdbcType jdbcType,
			String jdbcTypeName, String jdbcStruct) throws SQLException {

		StructDescriptor structDescriptor = StructDescriptor.createDescriptor(jdbcStruct, ps.getConnection());
		STRUCT[] structs = new STRUCT[pushFetchList.size()];
		for (int index = 0; index < pushFetchList.size(); index++) {

			PushFetchVO pushFetch = pushFetchList.get(index);
			Object[] params = new Object[10];
			params[0] = Util.convertIntegerToBigDecimal(pushFetch.getSlId());
			params[1] = pushFetch.getMessageName();
			params[2] = pushFetch.getSubject();
			params[3] = pushFetch.getFromRole();
			params[4] = pushFetch.getToRole();
			params[5] = pushFetch.getStatus();
			params[6] = pushFetch.getMoreInfoRole();
			params[7] = pushFetch.getItemKey();
			params[8] = Util.convertIntegerToBigDecimal(pushFetch.getNotificationId());
			params[9] = pushFetch.getNavigationPath();

			STRUCT struct = new STRUCT(structDescriptor, ps.getConnection(), params);
			structs[index] = struct;

			ArrayDescriptor desc = ArrayDescriptor.createDescriptor(jdbcTypeName, ps.getConnection());
			ARRAY oracleArray = new ARRAY(desc, ps.getConnection(), structs);
			ps.setArray(columnIndex, oracleArray);

		}

	}
}
