package com.wrj.netty.util;

/**
 * @ClassName Response
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-10 19:42
 * @Version 1.0
 */
public class Response {
    private Long id;
    private Object result;

    private String code="00000";
    private String msg;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
