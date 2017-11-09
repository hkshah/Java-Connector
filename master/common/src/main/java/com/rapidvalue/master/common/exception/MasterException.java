package com.rapidvalue.master.common.exception;

/**
 * 
 * @author Rohit.Nagesh
 * @since Apr 26, 2016
 */
public class MasterException extends Exception {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private Throwable throwable;

    public MasterException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public MasterException(int code, String message, Throwable throwable) {
        super();
        this.code = code;
        this.message = message;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}