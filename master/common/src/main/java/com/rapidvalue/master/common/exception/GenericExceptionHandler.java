package com.rapidvalue.master.common.exception;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rapidvalue.master.common.vo.BaseResponseVO;

/**
 * Exception handler to catch all exceptions and respond with corresponding
 * response code and message.
 * 
 * @author eldho.alias
 * @since Apr 26, 2016
 */
@ControllerAdvice
public class GenericExceptionHandler implements ExceptionConstant {

	private static final Logger LOG = Logger
			.getLogger(GenericExceptionHandler.class);

	/**
	 * Get the error code and message corresponding to the type of exception.
	 * This response is returned as the API response.
	 * 
	 * @param exception
	 *            {@link Exception}
	 * @return {@link BaseResponseVO}
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody BaseResponseVO handleException(
			final Exception exception) {

		LOG.error(exception);
		BaseResponseVO response = new BaseResponseVO();
		if (exception instanceof ConstraintViolationException) {
			response.setReturnCode(BAD_REQUEST_CODE);
			response.setReturnMsg(UNEXPECTED_VALUE);
		} else if (exception instanceof MethodArgumentNotValidException
				|| exception instanceof MissingServletRequestParameterException) {
			response.setReturnCode(BAD_REQUEST_CODE);
			response.setReturnMsg(MANDATORY_FIELDS_MISSING_MSG);
		} else if (exception instanceof HttpRequestMethodNotSupportedException) {
			response.setReturnCode(BAD_REQUEST_CODE);
			response.setReturnMsg(METHOD_NOT_SUPPORTED);
		} else if (exception instanceof HttpMessageNotReadableException) {
			response.setReturnCode(BAD_REQUEST_CODE);
			response.setReturnMsg(REQUEST_BODY_MISSING);
		} else if (exception instanceof MasterException) {
			MasterException e = (MasterException) exception;
			response.setReturnCode(e.getCode());
			response.setReturnMsg(e.getMessage());
		} else {
			response.setReturnCode(INTERNAL_SERVER_ERROR_CODE);
			response.setReturnMsg(SERVER_ERROR);
		}

		return response;
	}
}
