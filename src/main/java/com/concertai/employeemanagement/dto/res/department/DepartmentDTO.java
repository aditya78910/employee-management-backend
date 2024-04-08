package com.concertai.employeemanagement.dto.res.department;

import com.concertai.employeemanagement.dto.res.employee.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class DepartmentDTO {
    private Integer id;
    private String name;
    private List<EmployeeDTO> employees;
}
