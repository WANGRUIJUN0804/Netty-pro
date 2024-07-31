package com.wrj.netty.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName Remote
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-12 15:57
 * @Version 1.0
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Remote {
    String value() default "";
}
