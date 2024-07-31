package com.wrj.netty.client;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName ClientRequest
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-10 19:34
 * @Version 1.0
 */

public class ClientRequest {
    private final long id;
    private Object content;

    private String command;
    private final AtomicLong aid = new AtomicLong(1);

    public ClientRequest() {
        id = aid.incrementAndGet();
    }
    public long getId() {
        return id;
    }

    public Object getContent() {
        return content;
    }
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    public void setContent(Object content) {
        this.content = content;
        System.out.println(content);
    }
}
