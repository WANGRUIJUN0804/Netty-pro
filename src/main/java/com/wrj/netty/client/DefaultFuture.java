package com.wrj.netty.client;

import com.wrj.netty.util.Response;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName DefaultFuture
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-10 19:37
 * @Version 1.0
 */
public class DefaultFuture {

    public final static ConcurrentHashMap<Long, DefaultFuture> allDefaultFuture = new ConcurrentHashMap<>();
    final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private Response response;

    public DefaultFuture(ClientRequest request) {
        System.out.println("6");
        allDefaultFuture.put(request.getId(), this);
    }

    // 注释
    public Response get() {
        lock.lock();
        try {
            while (!done()) {
                condition.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return this.response;
    }
    public static void receive(Response response) {
        DefaultFuture df = allDefaultFuture.get(response.getId());
        if (df != null) {
            Lock lock = df.lock;
            lock.lock();
            try {
                df.setResponse(response);
                df.condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
    private boolean done() {
        if (this.response != null) {
            return true;
        }
        return false;
    }

}
