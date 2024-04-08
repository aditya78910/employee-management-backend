package com.concertai.employeemanagement.exceptions;


import com.concertai.employeemanagement.exceptions.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ EmployeeNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(
            EmployeeNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getErrorResponse(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ DepartmentNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleDepartmentNotFoundException(
            DepartmentNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getErrorResponse(), HttpStatus.NOT_FOUND);
    }

}
