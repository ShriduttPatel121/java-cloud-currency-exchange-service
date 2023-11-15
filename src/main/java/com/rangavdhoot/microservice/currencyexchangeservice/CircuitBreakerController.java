package com.rangavdhoot.microservice.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    //@Retry(name = "default", fallbackMethod = "fallback")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    @RateLimiter(name="default")
    @Bulkhead(name = "default")
    public String sampleAPI() {
        logger.info("Sample API call receivedSample API call received");
        // ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/dummy", String.class);
        // return response.getBody();
        return "message";
    }

    public String fallback(Exception e) {
        return "Fallback message";
    }
}

