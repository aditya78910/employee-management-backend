package com.concertai.employeemanagement.dto.res.employee;


import com.concertai.employeemanagement.entity.Department;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SingleEmployeeDTO {
    private Long id;

    private String name;

    private LocalDate dob;

    private Character gender;

    private Department department;

    private Integer salary;
}
