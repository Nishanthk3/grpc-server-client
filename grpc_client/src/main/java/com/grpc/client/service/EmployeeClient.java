package com.grpc.client.service;

import com.grpc.common.employee.Employee;
import com.grpc.common.employee.EmployeeRequest;
import com.grpc.common.employee.EmployeeResponse;
import com.grpc.common.employee.EmployeeServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeClient {

    static ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5003).usePlaintext().build();

    public void callGrpcService() {

        log.info("Create Single Employee");
        EmployeeServiceGrpc.EmployeeServiceBlockingStub stub = EmployeeServiceGrpc.newBlockingStub(channel);

        createSingleEmployee(stub);

        log.info("Create Multiple Employees");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        createMultipleEmployees(stub);
    }

    private static void createSingleEmployee(EmployeeServiceGrpc.EmployeeServiceBlockingStub stub) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employeeObject("single"));
        EmployeeRequest request = EmployeeRequest.newBuilder().addAllEmployee(employeeList).build();

        EmployeeResponse response = stub.create(request);
        log.info("Employee created with Id: {}", response.getId());
    }

    private static void createMultipleEmployees(EmployeeServiceGrpc.EmployeeServiceBlockingStub stub) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employeeObject("multiple_1"));
        employeeList.add(employeeObject("multiple_2"));
        EmployeeRequest request = EmployeeRequest.newBuilder().addAllEmployee(employeeList).build();

        log.info("Response for the second call: ");
        stub.createMultiple(request).forEachRemaining(i -> {
            log.info("Employee created with Id: {}", i.getId());
        });
    }

    private static Employee employeeObject(String name) {
        return Employee.newBuilder().setName(name).build();
    }
}
