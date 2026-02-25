package mx.com.ms.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HeaderLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(HeaderLoggingAspect.class);

    private static final String[] SENSITIVE_HEADERS = {"authorization", "cookie"};

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logRequestHeaders(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes attrs) {
            HttpServletRequest request = attrs.getRequest();

            String requestId = java.util.UUID.randomUUID().toString();
            request.setAttribute("requestId", requestId);

            logger.info("Incoming request [{} {}] - RequestId={}", request.getMethod(), request.getRequestURI(), requestId);

            Collections.list(request.getHeaderNames())
                .stream()
                .filter(header -> !isSensitive(header))
                .forEach(header -> logger.info("Header [{}]={}", header, request.getHeader(header)));
        }
    }

    private boolean isSensitive(String header) {
        for (String s : SENSITIVE_HEADERS) {
            if (s.equalsIgnoreCase(header)) return true;
        }
        return false;
    }
}