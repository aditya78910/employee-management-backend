package com.concertai.employeemanagement.mapper.department;

import com.concertai.employeemanagement.entity.Department;
import com.concertai.employeemanagement.dto.req.department.CreateDepartmentRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateDepartmentRequest_Department_Mapper {
    public Department createDepartment(CreateDepartmentRequestDTO createDepartmentRequestDTO){
        return Department.builder()
                .name(createDepartmentRequestDTO.getName())
                .build();
    }
}
