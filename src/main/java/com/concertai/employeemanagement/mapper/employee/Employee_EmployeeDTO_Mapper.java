package com.concertai.employeemanagement.mapper.employee;

import com.concertai.employeemanagement.entity.Employee;
import com.concertai.employeemanagement.dto.res.employee.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class Employee_EmployeeDTO_Mapper {
    public EmployeeDTO createEmployeeDTO(Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .department(employee.getDepartment())
                .gender(employee.getGender())
                .dob(employee.getDob())
                .build();
    }
}
