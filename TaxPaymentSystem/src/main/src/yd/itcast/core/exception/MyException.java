/*
信息:
*/
package yd.itcast.core.exception;

public class MyException extends Exception{
    private String errorMessage;

    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
        errorMessage=message;
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
        errorMessage=message;
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
