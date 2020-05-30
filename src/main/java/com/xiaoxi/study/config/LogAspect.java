package com.xiaoxi.study.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author liuteng
 */
@Aspect
@Component
@Order(1)
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        doBefore(joinPoint);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj = joinPoint.proceed();
        stopWatch.stop();
        doAfter(obj, stopWatch.getTotalTimeMillis());
        return obj;
    }



    private void doBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        List<String> headers = new ArrayList<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.add(headerName + " = " + request.getHeader(headerName));
        }
        String message = "\n 请求明细：\n【请求地址】：{}\n【请 求 头】：{}\n【请求方法】：{}\n【请求参数】：{}";
        StringBuffer url = request.getRequestURL();
        logger.info(message, url.toString(), String.join(", ", headers), joinPoint.getSignature(), JSON.toJSONString(joinPoint.getArgs()));
    }

    private void doAfter(Object obj, long totalMillis) {
        String message = "\n 执行结果：\n【响应结果】：{}\n【执行耗时】：{}毫秒";
        logger.info(message, JSON.toJSONString(obj), totalMillis);
    }
}
