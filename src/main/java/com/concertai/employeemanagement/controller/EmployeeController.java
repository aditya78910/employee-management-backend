package com.concertai.employeemanagement.controller;

import com.concertai.employeemanagement.dto.req.employee.CreateEmployeeRequestDTO;
import com.concertai.employeemanagement.dto.req.employee.UpdateEmployeeRequestDTO;
import com.concertai.employeemanagement.dto.res.employee.EmployeeDTO;
import com.concertai.employeemanagement.dto.res.employee.SingleEmployeeDTO;
import com.concertai.employeemanagement.security.model.UserProfile;
import com.concertai.employeemanagement.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  The endpoints are accessible to users with appropriate permissions which
 *   is validated by the annotation which invokes the AccessPermissionEvaluator,
 *   which in turn fetches the permissions from the access control microservice.
 *
 */
@RequestMapping("/employee")
@RestController
@AllArgsConstructor
public class EmployeeController {

    EmployeeService employeeService;

    /**
     *  Creates an Employee.
     *  Accessible to a user only if he has the
     *  'employee:permission'
     *
     */
    @PostMapping
    @PreAuthorize("hasPermission(#id, 'EMPLOYEE', 'employee:create')")
    public EmployeeDTO createEmployee(@RequestBody CreateEmployeeRequestDTO createEmployeeRequestDTO){
        return employeeService.createEmployee(createEmployeeRequestDTO);
    }

    @GetMapping
    @PreAuthorize("hasPermission(#id, 'EMPLOYEE', 'employee:viewall')")
    public List<EmployeeDTO> getEmployees(Authentication authentication){
        return employeeService.findEmployees();
    }


    /**
     *  Get an Employee details.
     *  Accessible to a user only if he has the
     *  'employee:view permission
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'EMPLOYEE', 'employee:view')")
    public SingleEmployeeDTO getEmployee(@PathVariable Long id, UserProfile userProfile){
        return employeeService.findEmployee(id);
    }

    /**
     *  Update an Employee details.
     *  Accessible to a user only if he has the
     *  'employee:update permission
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'EMPLOYEE', 'employee:update')")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeRequestDTO updateEmployeeRequestDTO) {
        return employeeService.updateEmployee(updateEmployeeRequestDTO, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'EMPLOYEE', 'employee:delete')")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

}
