package com.concertai.employeemanagement.service;


import com.concertai.employeemanagement.entity.Department;
import com.concertai.employeemanagement.exceptions.DepartmentNotFoundException;
import com.concertai.employeemanagement.exceptions.model.ErrorResponse;
import com.concertai.employeemanagement.mapper.department.CreateDepartmentRequest_Department_Mapper;
import com.concertai.employeemanagement.mapper.department.Department_DepartmentDTO_Mapper;
import com.concertai.employeemanagement.mapper.department.UpdateDepartmentRequest_Department_Mapper;
import com.concertai.employeemanagement.dto.req.department.CreateDepartmentRequestDTO;
import com.concertai.employeemanagement.dto.req.department.UpdateDepartmentRequestDTO;
import com.concertai.employeemanagement.dto.res.department.DepartmentDTO;
import com.concertai.employeemanagement.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService {


    DepartmentRepository departmentRepository;

    Department_DepartmentDTO_Mapper department_departmentDTO_mapper;

    CreateDepartmentRequest_Department_Mapper createDepartmentRequest_department_mapper;

    UpdateDepartmentRequest_Department_Mapper updateDepartmentRequest_department_mapper;

    public List<DepartmentDTO> findDepartments(){
        return departmentRepository.findAll()
                .stream().map(department_departmentDTO_mapper::createDepartmentDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(CreateDepartmentRequestDTO createDepartmentRequestDTO){
        Department department = createDepartmentRequest_department_mapper.createDepartment(createDepartmentRequestDTO);
        Department savedDepartment =  departmentRepository.save(department);
        return department_departmentDTO_mapper.createDepartmentDTO(savedDepartment);

    }

    public DepartmentDTO updateDepartment(UpdateDepartmentRequestDTO updateDepartmentRequestDTO, Integer id){
        Department toBeUpdatedDepartment = updateDepartmentRequest_department_mapper.createDepartment(id, updateDepartmentRequestDTO);


        Department updatedDepartment =  departmentRepository.save(toBeUpdatedDepartment);

        return department_departmentDTO_mapper.createDepartmentDTO(updatedDepartment);

    }
    public DepartmentDTO getDepartment(Long id){
        return departmentRepository.findById(id)
                .map(department_departmentDTO_mapper::createDepartmentDTO)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(ErrorResponse.builder()
                                .message("Department not found")
                                .description(String.format("Department not found : %s", id))
                                .build()));
    }

    public void deleteEmployee(@PathVariable Long id) {
        departmentRepository.deleteById(id);
    }
}
