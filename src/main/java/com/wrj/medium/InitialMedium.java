package com.wrj.medium;

import com.wrj.netty.annotation.Remote;
import com.wrj.netty.annotation.RemoteInvoke;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName InitialMedium
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-11 15:07
 * @Version 1.0
 */
@Component
public class InitialMedium implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(Remote.class)){
          Method[] methods= bean.getClass().getDeclaredMethods();
          for(Method m:methods){
              String key=bean.getClass().getInterfaces()[0].getName()+"."+m.getName();
              Map<String,BeanMethod> beanMap=Media.beanMap;
              BeanMethod beanMethod=new BeanMethod();
              beanMethod.setBean(bean);
              beanMethod.setMethod(m);
              beanMap.put(key,beanMethod);
          }
        }
        return bean;
    }
}
