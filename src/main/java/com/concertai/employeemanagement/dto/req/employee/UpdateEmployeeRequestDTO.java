package com.concertai.employeemanagement.dto.req.employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateEmployeeRequestDTO {

    private String name;
    private LocalDate dob;
    private Character gender;
    private Integer departmentId;
    private Integer salary;
}
