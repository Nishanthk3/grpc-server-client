package com.grpc.client.controller;

import com.grpc.client.service.EmployeeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GrpcClientController {

    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping(value = "/grpc/emp")
    public void grpcEmp() {
        log.info("Employee Service GRPC method");
        employeeClient.callGrpcService();
    }
}
