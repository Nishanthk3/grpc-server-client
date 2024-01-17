package com.grpc.server;

import com.grpc.common.employee.Employee;
import com.grpc.common.employee.EmployeeRequest;
import com.grpc.common.employee.EmployeeResponse;
import com.grpc.common.employee.EmployeeServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Override
    public void create(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
        log.info("Request received from client for single employee creation");

        Employee emp = request.getEmployee(0);
        log.info("[Employee Created]: {} {}", emp.getId(), emp.getName());

        EmployeeResponse response = EmployeeResponse.newBuilder().setId(UUID.randomUUID().toString()).build();

        // Send the response to the client.
        responseObserver.onNext(response);
        // Notifies that the call is completed.
        responseObserver.onCompleted();
    }

    @Override
    public void createMultiple(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
        log.info("Request received from client for multiple employee creation");

        for (Employee emp : request.getEmployeeList()) {
            log.info("[Employee Created]: {} {}", emp.getId(), emp.getName());

            EmployeeResponse response = EmployeeResponse.newBuilder().setId(UUID.randomUUID().toString()).build();

            /** Send the response to the client immediately after creation before creating the second employee object. */
            responseObserver.onNext(response);
        }
        // Notifies that the call is completed.
        responseObserver.onCompleted();
    }
}