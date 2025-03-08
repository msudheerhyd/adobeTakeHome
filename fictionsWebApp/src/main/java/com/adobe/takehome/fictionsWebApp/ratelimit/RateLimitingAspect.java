package com.adobe.takehome.fictionsWebApp.ratelimit;

import io.github.bucket4j.ConsumptionProbe;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import io.github.bucket4j.Bucket;

@Aspect
@Component
public class RateLimitingAspect {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitingAspect.class);

    @Autowired
    private Bucket bucket;

    @Around("@annotation(com.adobe.takehome.fictionsWebApp.ratelimit.RateLimited)")
    public Object applyRateLimiting(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Rate limiting applied for method: " + joinPoint.getSignature().getName());
        System.out.println("Hello");
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return joinPoint.proceed();
        } else {
            long waitTime = probe.getNanosToWaitForRefill() / 1_000_000_000;
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .header("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitTime))
                    .body("Too many requests. Please try again in " + waitTime + " seconds.");
        }
    }
}
