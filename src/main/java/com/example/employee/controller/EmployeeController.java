package com.example.employee.controller;

import com.example.employee.impl.EmployeeServiceImpl;
import com.example.employee.request.EmployeeRequest;
import com.example.employee.response.EmployeeResponse;
import com.example.employee.to.EmployeeDetailsTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping("/createemployeedata")
    public EmployeeResponse createEmployeeData(@RequestBody EmployeeRequest employeeRequest) {
        logger.info("START createEmployeedata() request() " + employeeRequest);
        EmployeeResponse employeeResponse = new EmployeeResponse();
        String responseMsg = "";
        //TODO We need to check null for each field

        if (employeeRequest != null) {

            try {
                responseMsg = employeeService.createEmployee(employeeRequest);
                employeeResponse.setMsg(responseMsg);
                employeeResponse.setSuccess(true);
            } catch (Exception exception) {
                employeeResponse.setMsg(responseMsg);
                employeeResponse.setSuccess(false);
                //TODO handle exception
            }

        } else {
            logger.error("Request can not be null");
        }
        logger.info("START createEmployeedata() CONTROLLER  RESPONSE{} " + employeeResponse);
        return employeeResponse;
    }


    @GetMapping("/getemployeedata")
    public EmployeeResponse getEmployeeData() {
        logger.info("START getAllEmployeeData() request() ");
        EmployeeResponse employeeResponse = new EmployeeResponse();

        String responseMsg = "";
        try {
            List<EmployeeDetailsTO> allEmployeeData = employeeService.getEmployee();
            employeeResponse.setMsg("List of Employee Data");
            employeeResponse.setSuccess(true);
            employeeResponse.setEmployeeDetailsTO(allEmployeeData);

        } catch (Exception e) {

            employeeResponse.setMsg("Exception occurred while getting the data");
            employeeResponse.setSuccess(false);

        }
        logger.info("END getAllEmployeeData() CONTROLLER RESPONSE{} " + employeeResponse);
        return employeeResponse;

    }

    @GetMapping("/getbyidemployeedata/{id}")
    public EmployeeResponse getByIdEmployeeData(@PathVariable int id) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        if (id != 0) {
            logger.info("START getByIdEmployeeData() request " + id);


            try {
                List<EmployeeDetailsTO> dataById = employeeService.getByIdEmployee(id);
                employeeResponse.setMsg("Data by given id");
                employeeResponse.setSuccess(true);
                employeeResponse.setEmployeeDetailsTO(dataById);

            } catch (Exception e) {
                employeeResponse.setMsg("Exception occured during data by given id");
                employeeResponse.setSuccess(false);

            }

        } else {
            employeeResponse.setMsg("ID should not be null");
            employeeResponse.setSuccess(false);

        }
        logger.info("END getByIdEmployeeData() Controller ");
        return employeeResponse;
    }


    @PutMapping("updateemployeedata/{id}")
    public EmployeeResponse updateEmployeeData(@PathVariable int id, @RequestBody EmployeeRequest request) {

        EmployeeResponse employeeResponse = new EmployeeResponse();
        String updateMessage = "";

        if (id != 0 && request != null) {
            logger.info("START updateEmployeeData() id{},request{} " + id + request);

            try {
                request.setEmployeeId(id);
                updateMessage = employeeService.updateEmployee(request);
                employeeResponse.setMsg(updateMessage);
                employeeResponse.setSuccess(true);

            } catch (Exception e) {
                employeeResponse.setMsg(updateMessage);
                employeeResponse.setSuccess(false);

            }

        } else {

            employeeResponse.setMsg("ID or/And request can not be null");
            employeeResponse.setSuccess(false);
        }
        logger.info("END updateEmployeeData() Controller Response" + employeeResponse);

        return employeeResponse;
    }

    @DeleteMapping("/deleteemployeedata/{id}")
    public EmployeeResponse deleteEmployeeData(@PathVariable int id) {
        String statusMessage = "";
        EmployeeResponse employeeResponse = new EmployeeResponse();

        if (id != 0) {
            logger.info("START deleteEmployeeData()  id{} " + id);

            try {
                statusMessage = employeeService.deleteEmployee(id);
                employeeResponse.setMsg(statusMessage);
                employeeResponse.setSuccess(true);

            } catch (Exception e) {
                employeeResponse.setMsg(statusMessage);
                employeeResponse.setSuccess(false);
            }

        } else {
            employeeResponse.setMsg("ID can not be null");
            employeeResponse.setSuccess(false);
        }
        return employeeResponse;
    }
}
