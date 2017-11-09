package com.rapidvalue.master.dao.impl;

import static com.rapidvalue.master.dao.constants.ConnectorConstants.MODE_IN;
import static com.rapidvalue.master.dao.constants.ConnectorConstants.MODE_IN_OUT;
import static com.rapidvalue.master.dao.constants.ConnectorConstants.MODE_OUT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidvalue.master.dao.ProcedureDao;
import com.rapidvalue.master.dao.constants.ConnectorConstants;
import com.rapidvalue.master.dao.exception.DaoException;
import com.rapidvalue.master.dao.mapper.ProcedureMapper;
import com.rapidvalue.master.dao.model.ProcedureParam;
import com.rapidvalue.master.dao.util.DomainDataProcessorUtil;
import com.rapidvalue.master.dao.util.ProcedureReader;

@Service
public class ProcedureDaoImpl implements ProcedureDao {

	@Autowired
	ProcedureMapper procedureMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rapidvalue.master.connector.dao.ConnectorDao#executeProcedure(java.
	 * util.Map)
	 */
	@Override
	public void execute(Map<String, Object> inOutParamsMap) {
		procedureMapper.execute(inOutParamsMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rapidvalue.master.connector.dao.ConnectorDao#executeProcedure(java.
	 * util.Map)
	 */
	@Override
	public void execute(String procedureName, Object inputOutput)
			throws DaoException {
		List<ProcedureParam> procedureParams = ProcedureReader.getInstance()
				.getProcedureParams(procedureName);
		Map<String, Object> inOutParamsMap = new HashMap<>();
		inOutParamsMap.put(ConnectorConstants.PROCEDURE_NAME, procedureName);
		for (ProcedureParam procedureParam : procedureParams) {
			if (MODE_IN.equalsIgnoreCase(procedureParam.getMode())
					|| MODE_IN_OUT.equalsIgnoreCase(procedureParam.getMode())) {
				String columnName = procedureParam.getValue();
				inOutParamsMap.put(columnName, DomainDataProcessorUtil
						.getValue(columnName, inputOutput));
			}
		}
		execute(inOutParamsMap);
		for (ProcedureParam procedureParam : procedureParams) {
			if (MODE_OUT.equalsIgnoreCase(procedureParam.getMode())
					|| MODE_IN_OUT.equalsIgnoreCase(procedureParam.getMode())) {
				String columnName = procedureParam.getValue();
				DomainDataProcessorUtil.setValues(inputOutput, columnName,
						inOutParamsMap.get(columnName));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rapidvalue.master.connector.dao.ConnectorDao#executeProcedure(java.
	 * lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void execute(String procedureName, Object input, Object output)
			throws DaoException {
		List<ProcedureParam> procedureParams = ProcedureReader.getInstance()
				.getProcedureParams(procedureName);
		Map<String, Object> inOutParamsMap = new HashMap<>();
		inOutParamsMap.put(ConnectorConstants.PROCEDURE_NAME, procedureName);
		for (ProcedureParam procedureParam : procedureParams) {
			if (MODE_IN.equalsIgnoreCase(procedureParam.getMode())
					|| MODE_IN_OUT.equalsIgnoreCase(procedureParam.getMode())) {
				String columnName = procedureParam.getValue();
				inOutParamsMap.put(columnName,
						DomainDataProcessorUtil.getValue(columnName, input));
			}
		}
		execute(inOutParamsMap);
		for (ProcedureParam procedureParam : procedureParams) {
			if (MODE_OUT.equalsIgnoreCase(procedureParam.getMode())
					|| MODE_IN_OUT.equalsIgnoreCase(procedureParam.getMode())) {
				String columnName = procedureParam.getValue();
				DomainDataProcessorUtil.setValues(output, columnName,
						inOutParamsMap.get(columnName));
			}
		}
	}

}
