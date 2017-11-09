package com.rapidvalue.master.dao.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.StatementType;

import com.rapidvalue.master.dao.util.SqlGenerator;

public interface ProcedureMapper {

    @SelectProvider(type = SqlGenerator.class, method = "getProcedure")
    @Options(statementType = StatementType.CALLABLE)
    public void execute(Map<String, Object> object);

}