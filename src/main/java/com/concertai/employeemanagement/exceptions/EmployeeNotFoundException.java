package com.concertai.employeemanagement.exceptions;

import com.concertai.employeemanagement.exceptions.model.ErrorResponse;
import lombok.Data;

@Data
public class EmployeeNotFoundException extends RuntimeException{

    private ErrorResponse errorResponse;
    public EmployeeNotFoundException(ErrorResponse errorResponse){
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public EmployeeNotFoundException(Throwable cause, ErrorResponse errorResponse){
        super(errorResponse.getMessage(), cause);
        this.errorResponse = errorResponse;
    }

}
