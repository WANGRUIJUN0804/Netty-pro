package com.wrj.netty.handler.param;

/**
 * @ClassName ServerRequest
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-10 20:26
 * @Version 1.0
 */
public class ServerRequest {
    private Long id;
    private Object content;
    private String command;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
