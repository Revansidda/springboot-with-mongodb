package com.example.employee.impl;

import com.example.employee.dao.EmployeeDAO;
import com.example.employee.model.EmployeeDetailsModel;
import com.example.employee.request.EmployeeRequest;
import com.example.employee.service.EmployeeService;
import com.example.employee.to.EmployeeDetailsTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeDAO employeeDAO;

    @Override
    public String createEmployee(EmployeeRequest employeeRequest) {

        logger.info("start - createEmployee{} service request{}" + employeeRequest);
        String messageStatus = "";
        EmployeeDetailsModel employeeDetails = new EmployeeDetailsModel();

        try {

            employeeDetails.setEmployeeId(employeeRequest.getEmployeeId());
            employeeDetails.setEmployeeEmail(employeeRequest.getEmployeeEmail());
            employeeDetails.setEmployeeName(employeeRequest.getEmployeeName());
            employeeDetails.setEmployeeSalary(employeeRequest.getEmployeeSalary());
            employeeDAO.insert(employeeDetails);
            messageStatus = "Data Inserted Successfully";

            // employeeRequest.getEmployeeId();
            // employeeRequest.getEmployeeEmail();
            // employeeRequest.getEmployeeName();
            //employeeRequest.getEmployeeSalary();

        } catch (Exception e) {
            //TODO: handle exception
            messageStatus = "Exception Occurred while creating the data";
            logger.error("Exception Occurred while inserting the Data" + messageStatus);
        }
        logger.info("end - createEmployee{} service response{}" + messageStatus);
        return messageStatus;
    }

    @Override
    public List<EmployeeDetailsTO> getEmployee() {
        logger.info("start - createEmployee{} service");
        List<EmployeeDetailsTO> listOf = new ArrayList<>();

        try {
            List<EmployeeDetailsModel> allEmployeeDetails = employeeDAO.findAll();

            for (EmployeeDetailsModel employeeDetailsModel : allEmployeeDetails) {

                EmployeeDetailsTO employeeDetailsTO = new EmployeeDetailsTO();

                employeeDetailsTO.setEmployeeId(employeeDetailsModel.getEmployeeId());
                employeeDetailsTO.setEmployeeName(employeeDetailsModel.getEmployeeName());
                employeeDetailsTO.setEmployeeEmail(employeeDetailsModel.getEmployeeEmail());
                employeeDetailsTO.setEmployeeSalary(employeeDetailsModel.getEmployeeSalary());
                listOf.add(employeeDetailsTO);


//             employeeDetailsModel.getEmployeeId();
//             employeeDetailsModel.getEmployeeName();
//             employeeDetailsModel.getEmployeeEmail();
//             employeeDetailsModel.getEmployeeSalary();
            }

        } catch (Exception e) {

            logger.error("Exception Occurred while fetching the data from DB");
        }

        logger.info("start - getEmployee{} service RESPONSE " + listOf);
        return listOf;

    }

    @Override
    public List<EmployeeDetailsTO> getByIdEmployee(int id) {
        logger.info("start - getByIdEmployee{} service");

        List<EmployeeDetailsTO> listOf = new ArrayList<>();
        EmployeeDetailsTO employeeDetailsTO = new EmployeeDetailsTO();

        try {
            Optional<EmployeeDetailsModel> databyId = employeeDAO.findById(id);
            employeeDetailsTO.setEmployeeId(databyId.get().getEmployeeId());
            employeeDetailsTO.setEmployeeEmail(databyId.get().getEmployeeEmail());
            employeeDetailsTO.setEmployeeSalary(databyId.get().getEmployeeSalary());
            employeeDetailsTO.setEmployeeName(databyId.get().getEmployeeName());
            listOf.add(employeeDetailsTO);

//        databyId.get().getEmployeeId();
//        databyId.get().getEmployeeName();
//        databyId.get().getEmployeeEmail();
//        databyId.get().getEmployeeSalary();


        } catch (Exception e) {
            logger.error("Exception Occurred while fetching the data by id from DB");
        }

        logger.info("start - getByIdEmployee{} service RESPONSE " + listOf);
        return listOf;
    }


    @Override
    public String updateEmployee(EmployeeRequest employeeRequest) {

        EmployeeDetailsModel employeeDetailsModel = new EmployeeDetailsModel();
        String msg = "";

        try {
            employeeDetailsModel.setEmployeeId(employeeRequest.getEmployeeId());
            employeeDetailsModel.setEmployeeName(employeeRequest.getEmployeeName());
            employeeDetailsModel.setEmployeeEmail(employeeRequest.getEmployeeName());
            employeeDetailsModel.setEmployeeSalary(employeeRequest.getEmployeeSalary());
            employeeDAO.save(employeeDetailsModel);
            msg = "Data Got Updated Successfully";

        } catch (Exception e) {
            msg = "Exception Occurred While during update";

        }

//         employeeRequest.getEmployeeId();
//        employeeRequest.getEmployeeName();
//        employeeRequest.getEmployeeEmail();
//        employeeRequest.getEmployeeSalary();


        return msg;
    }

    @Override
    public String deleteEmployee(int id) {

        String msg = "";
        try {
            employeeDAO.deleteById(id);
            msg = "Data got deleted successfully";
        } catch (Exception e) {
            msg = "Exception has occured while deleting the data";

        }
        return msg;

    }
}
