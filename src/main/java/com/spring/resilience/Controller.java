package com.spring.resilience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    SampleService sampleService;

    @GetMapping("/test")
    public String testResilience(){
        return sampleService.test();
    }
}
