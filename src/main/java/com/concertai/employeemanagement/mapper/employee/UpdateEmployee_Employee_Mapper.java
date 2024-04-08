package com.concertai.employeemanagement.mapper.employee;

import com.concertai.employeemanagement.entity.Employee;
import com.concertai.employeemanagement.dto.req.employee.UpdateEmployeeRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UpdateEmployee_Employee_Mapper {
    public Employee createEmployeeFromRequestDTO(Long id, UpdateEmployeeRequestDTO updateEmployeeRequestDTO){
        return Employee.builder()
                .id(id)
                .name(updateEmployeeRequestDTO.getName())
                .gender(updateEmployeeRequestDTO.getGender())
                .dob(updateEmployeeRequestDTO.getDob())
                .salary(updateEmployeeRequestDTO.getSalary())
                .build();
    }
}