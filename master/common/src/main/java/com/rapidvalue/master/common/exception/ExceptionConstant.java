package com.rapidvalue.master.common.exception;

/**
 * An interface to declare all constants used in GenericExceptionHandler.java
 * 
 * @author eldho.alias
 * @since Apr 27, 2016
 */
public interface ExceptionConstant {

    Integer BAD_REQUEST_CODE = 400;
    Integer INTERNAL_SERVER_ERROR_CODE = 500;

    String METHOD_NOT_SUPPORTED = "Request method not supported.";
    String REQUEST_BODY_MISSING = "Required request body is missing.";
    String UNEXPECTED_VALUE = "Please provide a valid request.";
    String SERVER_ERROR = "We are unable to process your request at this time. Please try after sometime.";
    String MANDATORY_FIELDS_MISSING_MSG = "Mandatory fields missing.";
}
