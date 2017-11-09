package com.rapidvalue.master.dao.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.rapidvalue.master.dao.constants.ConnectorConstants;
import com.rapidvalue.master.dao.exception.DaoException;
import com.rapidvalue.master.dao.model.ProcedureParam;

public class SqlGenerator {

	private static final Logger LOG = Logger.getLogger(SqlGenerator.class);

	public static String getProcedure(Map<String, Object> params)
			throws DaoException {
		String sql = null;
		String procedureName = (String) params
				.get(ConnectorConstants.PROCEDURE_NAME);
		try {
			List<ProcedureParam> procedureParams = ProcedureReader
					.getInstance().getProcedureParams(procedureName);
			sql = "{call " + procedureName + "(";
			boolean flag = false;
			for (ProcedureParam procedureParam : procedureParams) {
				if (flag)
					sql = sql + ", ";
				sql = sql + "#{" + procedureParam.getValue();
				if (StringUtils.isNotBlank(procedureParam.getMode())) {
					sql = sql + ", mode=" + procedureParam.getMode();
				}
				if (StringUtils.isNotBlank(procedureParam.getJdbcType())) {
					sql = sql + ", jdbcType=" + procedureParam.getJdbcType();
				}
				if (StringUtils.isNotBlank(procedureParam.getJdbcTypeName())) {
					sql = sql + ", jdbcTypeName="
							+ procedureParam.getJdbcTypeName();
				}
				if (StringUtils.isNotBlank(procedureParam.getTypeHandler())) {
					sql = sql + ", typeHandler="
							+ procedureParam.getTypeHandler();
				}
				sql = sql + "}";
				flag = true;
			}
			sql = sql + ")}";
		} catch (DaoException e) {
			LOG.error("Failed to execute procedure::\n" + e);
			throw new DaoException(500, "Failed to execute procedure.", e);
		}
		return sql;
	}
}
