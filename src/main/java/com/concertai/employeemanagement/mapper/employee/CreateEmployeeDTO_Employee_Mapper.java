package com.concertai.employeemanagement.mapper.employee;

import com.concertai.employeemanagement.entity.Employee;
import com.concertai.employeemanagement.dto.req.employee.CreateEmployeeRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateEmployeeDTO_Employee_Mapper {
    public Employee createEmployeeFromRequestDTO(CreateEmployeeRequestDTO createEmployeeRequestDTO){
        return Employee.builder()
                .id(0L)
                .name(createEmployeeRequestDTO.getName())
                .gender(createEmployeeRequestDTO.getGender())
                .dob(createEmployeeRequestDTO.getDob())
                .salary(createEmployeeRequestDTO.getSalary())
                .build();
    }
}