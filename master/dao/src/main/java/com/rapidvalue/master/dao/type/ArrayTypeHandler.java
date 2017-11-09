package com.rapidvalue.master.dao.type;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;

/**
 * @author rohit.nagesh
 *
 */
public abstract class ArrayTypeHandler<T> implements org.apache.ibatis.type.TypeHandler<T> {

    @Override
    public final void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        setParameter(ps, i, parameter, jdbcType, null, null);
    }

    public abstract void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType, String jdbcTypeName,
            String jdbcStruct) throws SQLException;

}
