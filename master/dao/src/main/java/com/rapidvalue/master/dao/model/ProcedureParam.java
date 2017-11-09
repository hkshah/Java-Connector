package com.rapidvalue.master.dao.model;

/**
 * 
 * @author Rohit Nagesh
 * @since April 28, 2016
 */
public class ProcedureParam {

    private String value;

    private String mode;

    private String jdbcType;

    private String jdbcTypeName;

    private String jdbcStruct;

    private String typeHandler;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName) {
        this.jdbcTypeName = jdbcTypeName;
    }

    public String getTypeHandler() {
        return typeHandler;
    }

    public void setTypeHandler(String typeHandler) {
        this.typeHandler = typeHandler;
    }

    /**
     * @return the jdbcStruct
     */
    public String getjdbcStruct() {
        return jdbcStruct;
    }

    /**
     * @param jdbcStruct
     *            the jdbcStruct to set
     */
    public void setjdbcStruct(String jdbcStruct) {
        this.jdbcStruct = jdbcStruct;
    }

}
