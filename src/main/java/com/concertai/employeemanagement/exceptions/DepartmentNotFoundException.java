package com.concertai.employeemanagement.exceptions;

import com.concertai.employeemanagement.exceptions.model.ErrorResponse;
import lombok.Data;

@Data
public class DepartmentNotFoundException extends RuntimeException{
    private ErrorResponse errorResponse;
    public DepartmentNotFoundException(ErrorResponse errorResponse){
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public DepartmentNotFoundException(Throwable cause, ErrorResponse errorResponse){
        super(errorResponse.getMessage(), cause);
        this.errorResponse = errorResponse;
    }
}
