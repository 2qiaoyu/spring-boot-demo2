package com.joham.demo.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @author joham
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    @Pointcut("@annotation(com.joham.demo.annotation.MyLog)")

    private void logPointCut() {

    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.debug("┌──────────请求──────────────────");
        log.debug("┃控制器{}->方法{}-->参数{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), JSON.toJSONString(Arrays.toString(joinPoint.getArgs())));
        log.debug("┃{}-->{}-->{}", request.getRemoteAddr(), request.getMethod(), request.getRequestURL());
        log.debug("┃parameter{}", JSON.toJSONString(request.getParameterMap()));
        log.debug("┃ip{}", getRemoteHost(request));
        log.debug("┃body{}", getPayload(request));
        log.debug("└──────────────────────────────");

    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // ob 为方法的返回值
        Object ob = pjp.proceed();
        log.debug("┌──────────回复──────────────────");
        log.debug("┃耗时{}ms", (System.currentTimeMillis() - startTime));
        return ob;
    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.debug("┃返回" + ret);
        log.debug("└──────────────────────────────");
    }

    private String getPayload(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    //最大只打印1024字节
                    int length = Math.min(buf.length, 1024);
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException var6) {
                    return "[unknown]";
                }
            }
        }
        return "";
    }

    /**
     * 获取目标主机的ip
     *
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        StringTokenizer st = new StringTokenizer(ip, ",");
        // 多级反向代理时，取第一个IP
        if (st.countTokens() > 1) {
            ip = st.nextToken();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
