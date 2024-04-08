package com.concertai.employeemanagement.controller;

import com.concertai.employeemanagement.dto.req.department.CreateDepartmentRequestDTO;
import com.concertai.employeemanagement.dto.req.department.UpdateDepartmentRequestDTO;
import com.concertai.employeemanagement.dto.res.department.DepartmentDTO;
import com.concertai.employeemanagement.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/department")
@RestController
@AllArgsConstructor
public class DepartmentController {

    DepartmentService departmentService;


    @PostMapping
    @PreAuthorize("hasPermission(#id, 'DEPARTMENT', 'department:create')")
    public DepartmentDTO createDepartment(@RequestBody CreateDepartmentRequestDTO createDepartmentRequestDTO){
        return departmentService.createDepartment(createDepartmentRequestDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'DEPARTMENT', 'department:update')")
    public DepartmentDTO updateDepartment(@PathVariable Integer id, @RequestBody UpdateDepartmentRequestDTO updateDepartmentRequestDTO){
        return departmentService.updateDepartment(updateDepartmentRequestDTO, id);
    }

    @GetMapping
    @PreAuthorize("hasPermission(#id, 'DEPARTMENT', 'department:viewall')")
    public List<DepartmentDTO> findDepartments(){
        return departmentService.findDepartments();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'DEPARTMENT', 'department:view')")
    public DepartmentDTO getDepartment(@PathVariable Long id){
        return departmentService.getDepartment(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'DEPARTMENT', 'department:delete')")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.deleteEmployee(id);
    }

}
