package com.rapidvalue.master.dao.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.rapidvalue.master.dao.exception.DaoException;
import com.rapidvalue.master.dao.model.ProcedureParam;
import com.rapidvalue.master.dao.type.ArrayTypeHandler;
import com.rapidvalue.master.dao.util.ProcedureReader;

public class CustomParameterHandler implements ParameterHandler {

	private final TypeHandlerRegistry typeHandlerRegistry;

	private final MappedStatement mappedStatement;
	private final Object parameterObject;
	private BoundSql boundSql;
	private Configuration configuration;

	public CustomParameterHandler(MappedStatement mappedStatement,
			Object parameterObject, BoundSql boundSql) {
		this.mappedStatement = mappedStatement;
		this.configuration = mappedStatement.getConfiguration();
		this.typeHandlerRegistry = mappedStatement.getConfiguration()
				.getTypeHandlerRegistry();
		this.parameterObject = parameterObject;
		this.boundSql = boundSql;
	}

	@Override
	public Object getParameterObject() {
		return parameterObject;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setParameters(PreparedStatement ps) {
		ErrorContext.instance().activity("setting parameters")
				.object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else {
						MetaObject metaObject = configuration
								.newMetaObject(parameterObject);
						value = metaObject.getValue(propertyName);
					}
					JdbcType jdbcType = parameterMapping.getJdbcType();
					if (value == null && jdbcType == null) {
						jdbcType = configuration.getJdbcTypeForNull();
					}
					try {
						if (jdbcType == JdbcType.ARRAY) {
							String sqlString = boundSql.getSql();
							String procedureName = sqlString.substring(6,
									sqlString.indexOf("(")).trim();
							List<ProcedureParam> procedureParams = ProcedureReader
									.getInstance().getProcedureParams(
											procedureName);
							ProcedureParam procedureParam = procedureParams
									.get(i);
							String jdbcTypeName = procedureParam
									.getJdbcTypeName();
							String jdbcTypeArray = procedureParam
									.getjdbcStruct();
							ArrayTypeHandler typeHandler = (ArrayTypeHandler) parameterMapping
									.getTypeHandler();
							typeHandler.setParameter(ps, i + 1, value,
									jdbcType, jdbcTypeName, jdbcTypeArray);
						} else if (jdbcType == JdbcType.BLOB) {
							org.apache.ibatis.type.BlobTypeHandler typeHandler = (org.apache.ibatis.type.BlobTypeHandler) parameterMapping
									.getTypeHandler();
							typeHandler.setParameter(ps, i + 1, (byte[]) value,
									jdbcType);
						} else {
							org.apache.ibatis.type.TypeHandler typeHandler = (org.apache.ibatis.type.TypeHandler) parameterMapping
									.getTypeHandler();
							typeHandler
									.setParameter(ps, i + 1, value, jdbcType);
						}
					} catch (TypeException e) {
						throw new TypeException(
								"Could not set parameters for mapping: "
										+ parameterMapping + ". Cause: " + e, e);
					} catch (SQLException e) {
						throw new TypeException(
								"Could not set parameters for mapping: "
										+ parameterMapping + ". Cause: " + e, e);
					} catch (DaoException e) {
						throw new TypeException(
								"Could not set parameters for mapping: "
										+ parameterMapping + ". Cause: " + e, e);
					}
				}
			}
		}
	}

}
