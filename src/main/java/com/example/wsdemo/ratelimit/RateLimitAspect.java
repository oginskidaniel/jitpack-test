/**
 * Copyright (c), Daniel Oginski,
 * Krakow, Poland
 *
 * All rights reserved. Dissemination, reproduction, or use of this material in source
 * and binary forms requires prior written permission from Daniel Oginski.
 */
package com.example.wsdemo.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RateLimitAspect {

    private final RateLimiter rateLimiter;

    public RateLimitAspect() {
        this.rateLimiter = RateLimiter.create(1.0); // Set the rate to 100 requests per second
    }

    @Around("@annotation(RateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        if (rateLimiter.tryAcquire()) {
            return joinPoint.proceed();
        } else {
            throw new RuntimeException("Rate limit exceeded");
        }
    }
}
