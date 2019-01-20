package com.hongzan.base;

/**
 * 用户自定义异常类
 */
public class UserException extends RuntimeException {

    private Throwable e;
    private String message = "您的上级未存在无法提交！";

    public UserException(){}

    public UserException(String message){
        this.message = message;
    }

    public UserException(Throwable e){
        this.e = e;
    }

    public UserException(Throwable e,String message){
        this.e = e;
        this.message = message;
    }

    public Throwable getE() {
        return e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
