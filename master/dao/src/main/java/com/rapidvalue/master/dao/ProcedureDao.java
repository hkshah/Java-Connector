package com.rapidvalue.master.dao;

import java.util.Map;

import com.rapidvalue.master.dao.constants.ConnectorConstants;
import com.rapidvalue.master.dao.exception.DaoException;

/**
 * Dao Class for executing SQL commands
 * 
 * @author rohit.nagesh
 */
public interface ProcedureDao {

    /**
     * @param Map
     *            <String, Object> Should pass the procedure name
     *            {@link ConnectorConstants}.PROCEDURE_NAME and the input
     *            values. The output values will be added to the map after
     *            execution.
     * 
     */
    public void execute(Map<String, Object> parameters);

    /**
     * @param procedureName
     * @param param
     * @throws DaoException
     */
    public void execute(String procedureName, Object inputOutput) throws DaoException;

    /**
     * @param procedureName
     * @param input
     * @param output
     * @throws DaoException
     */
    public void execute(String procedureName, Object input, Object output) throws DaoException;

}