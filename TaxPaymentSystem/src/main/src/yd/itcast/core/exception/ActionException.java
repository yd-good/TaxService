/*
信息:
*/
package yd.itcast.core.exception;

public class ActionException extends MyException{
    public ActionException() {
        super("请求时发生错误");
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
