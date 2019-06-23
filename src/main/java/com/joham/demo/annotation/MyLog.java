package com.joham.demo.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default "日志注解";
}
