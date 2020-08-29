/*
信息:
*/
package yd.itcast.core.exception;

public class ServiceException extends MyException{

    public ServiceException() {
        super("业务操作失败！");
    }

    public ServiceException(String message) {
        super(message);
    }
}
