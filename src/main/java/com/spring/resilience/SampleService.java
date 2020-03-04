package com.spring.resilience;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class SampleService {
    Logger log = LoggerFactory.getLogger(SampleService.class);

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "serviceA", fallbackMethod = "fallBack")
    public String test(){

        String response = null;
            response = restTemplate.getForObject("http://localhost:8082/exception", String.class);
        return response;
    }

    public String fallBack(Throwable throwable){
        log.info("exception : "+throwable.getCause());
        return "fallback method called by ciricuit breaker";
    }
}
