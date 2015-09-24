package com.lhs.weichat.bean;

/**
 * Result
 *
 * @author longhuashen
 * @since 15/9/24
 */
public class Result {

    /**
     * 请求处理是否成功
     */
    private boolean success;

    /**
     * 返回的文本提示消息
     */
    private String message;

    /**
     * 附加业务对象
     */
    private Object obj;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, String message, Object obj) {
        this.success = success;
        this.message = message;
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
