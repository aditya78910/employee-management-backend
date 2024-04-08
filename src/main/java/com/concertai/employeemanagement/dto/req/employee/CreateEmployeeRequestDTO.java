package com.concertai.employeemanagement.dto.req.employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEmployeeRequestDTO{
    private String name;
    private LocalDate dob;
    private Character gender;
    private Long departmentId;
    private Integer salary;
}
