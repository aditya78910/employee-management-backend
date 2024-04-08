package com.concertai.employeemanagement.service;

import com.concertai.employeemanagement.entity.Department;
import com.concertai.employeemanagement.entity.Employee;
import com.concertai.employeemanagement.exceptions.EmployeeNotFoundException;
import com.concertai.employeemanagement.exceptions.model.ErrorResponse;
import com.concertai.employeemanagement.mapper.employee.CreateEmployeeDTO_Employee_Mapper;
import com.concertai.employeemanagement.mapper.employee.Employee_EmployeeDTO_Mapper;
import com.concertai.employeemanagement.mapper.employee.Employee_SingleEmployeeDTO_Mapper;
import com.concertai.employeemanagement.mapper.employee.UpdateEmployee_Employee_Mapper;
import com.concertai.employeemanagement.dto.req.employee.CreateEmployeeRequestDTO;
import com.concertai.employeemanagement.dto.req.employee.UpdateEmployeeRequestDTO;
import com.concertai.employeemanagement.dto.res.employee.EmployeeDTO;
import com.concertai.employeemanagement.dto.res.employee.SingleEmployeeDTO;
import com.concertai.employeemanagement.repository.DepartmentRepository;
import com.concertai.employeemanagement.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    EmployeeRepository employeeRepository;

    DepartmentService departmentService;

    DepartmentRepository departmentRepository;

    CreateEmployeeDTO_Employee_Mapper createEmployeeDTO_employee_mapper;

    Employee_EmployeeDTO_Mapper employee_employeeDTO_mapper;

    Employee_SingleEmployeeDTO_Mapper employee_singleEmployeeDTO_mapper;

    UpdateEmployee_Employee_Mapper updateEmployee_employee_mapper;

    public List<EmployeeDTO> findEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return  employees
                .stream().map(employee_employeeDTO_mapper::createEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(CreateEmployeeRequestDTO createEmployeeRequestDTO){
        Department department = null;
        if(!ObjectUtils.isEmpty(createEmployeeRequestDTO.getDepartmentId())) {
            department = departmentRepository.findById(createEmployeeRequestDTO.getDepartmentId())
                    .orElse(null);
        }
        Employee employee = createEmployeeDTO_employee_mapper.createEmployeeFromRequestDTO(createEmployeeRequestDTO);
        employee.setDepartment(department);
        Employee savedEmployee =  employeeRepository.save(employee);
        return employee_employeeDTO_mapper.createEmployeeDTO(savedEmployee);

    }

    public EmployeeDTO updateEmployee(UpdateEmployeeRequestDTO updateEmployeeRequestDTO, Long id){
        Employee toBeUpdatedEmployee = updateEmployee_employee_mapper.createEmployeeFromRequestDTO(id, updateEmployeeRequestDTO);

        if(!ObjectUtils.isEmpty(updateEmployeeRequestDTO.getDepartmentId())){
            Department department = new Department();
            department.setId(updateEmployeeRequestDTO.getDepartmentId());
            toBeUpdatedEmployee.setDepartment(department);
        }

        Employee updatedEmployee =  employeeRepository.save(toBeUpdatedEmployee);

        return employee_employeeDTO_mapper.createEmployeeDTO(updatedEmployee);

    }

    public SingleEmployeeDTO findEmployee(Long id){
        Employee employee =  employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(ErrorResponse.builder()
                                .message("Employee not found")
                                .description(String.format("Employee not found : %s", id))
                                .build()));

        return employee_singleEmployeeDTO_mapper.createSingleEmployeeDTO(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

}
