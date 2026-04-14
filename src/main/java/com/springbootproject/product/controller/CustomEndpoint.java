package com.springbootproject.product.controller;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom")
public class CustomEndpoint {
    // url ->  localhost:8080/actuator/custom
    @ReadOperation
    public String getCustom(){
        return "this is Custom Endpoint";
    }
}
