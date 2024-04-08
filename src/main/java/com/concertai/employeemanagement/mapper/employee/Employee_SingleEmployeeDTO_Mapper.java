package com.concertai.employeemanagement.mapper.employee;

import com.concertai.employeemanagement.entity.Employee;
import com.concertai.employeemanagement.dto.res.employee.SingleEmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class Employee_SingleEmployeeDTO_Mapper {
    public SingleEmployeeDTO createSingleEmployeeDTO(Employee employee){
        return SingleEmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .department(employee.getDepartment())
                .gender(employee.getGender())
                .dob(employee.getDob())
                .salary(employee.getSalary())
                .build();
    }
}
