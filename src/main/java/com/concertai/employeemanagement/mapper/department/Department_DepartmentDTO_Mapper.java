package com.concertai.employeemanagement.mapper.department;

import com.concertai.employeemanagement.entity.Department;
import com.concertai.employeemanagement.mapper.employee.Employee_EmployeeDTO_Mapper;
import com.concertai.employeemanagement.dto.res.department.DepartmentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Department_DepartmentDTO_Mapper {

    Employee_EmployeeDTO_Mapper employee_employeeDTO_mapper;
   public DepartmentDTO createDepartmentDTO(Department department){

       return DepartmentDTO.builder()
               .id(department.getId())
               .name(department.getName())
               .build();
   }
}
