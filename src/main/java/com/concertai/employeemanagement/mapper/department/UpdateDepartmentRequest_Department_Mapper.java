package com.concertai.employeemanagement.mapper.department;

import com.concertai.employeemanagement.entity.Department;
import com.concertai.employeemanagement.dto.req.department.UpdateDepartmentRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UpdateDepartmentRequest_Department_Mapper {
    public Department createDepartment(Integer id, UpdateDepartmentRequestDTO updateDepartmentRequestDTO){
        return Department.builder()
                .id(id)
                .name(updateDepartmentRequestDTO.getName())
                .build();
    }
}
