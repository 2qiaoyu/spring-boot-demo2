package com.joham.demo.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 自定义缓存的 key生成
 *
 * @author joham
 */
@Component
public class MyKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(o.getClass().getSimpleName())
                .append("_")
                .append(method.getName())
                .append("_")
                .append(StringUtils.arrayToDelimitedString(objects, "_"));
        return stringBuffer.toString();
    }
}
