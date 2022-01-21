package com.example.employee.response;

import com.example.employee.to.EmployeeDetailsTO;
import java.util.List;

public class EmployeeResponse {

   private String msg;
   boolean isSuccess;

  List<EmployeeDetailsTO>  employeeDetailsTO;

    public EmployeeResponse() {
    }

    public EmployeeResponse(String msg, boolean isSuccess, List<EmployeeDetailsTO> employeeDetailsTO) {
        this.msg = msg;
        this.isSuccess = isSuccess;
        this.employeeDetailsTO = employeeDetailsTO;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<EmployeeDetailsTO> getEmployeeDetailsTO() {
        return employeeDetailsTO;
    }

    public void setEmployeeDetailsTO(List<EmployeeDetailsTO> employeeDetailsTO) {
        this.employeeDetailsTO = employeeDetailsTO;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "msg='" + msg + '\'' +
                ", isSuccess=" + isSuccess +
                ", employeeDetailsTO=" + employeeDetailsTO +
                '}';
    }
}
