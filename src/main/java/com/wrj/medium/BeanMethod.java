package com.wrj.medium;

import java.lang.reflect.Method;

/**
 * @ClassName BeanMethod
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-11 15:26
 * @Version 1.0
 */
public class BeanMethod {
    public void setBean(Object bean) {
        this.bean = bean;
    }

    private Object bean;

    private Method method;
    public Object getBean() {
        return bean;
    }
    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
