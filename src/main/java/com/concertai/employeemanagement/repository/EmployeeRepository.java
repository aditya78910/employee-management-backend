package com.concertai.employeemanagement.repository;

import com.concertai.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @EntityGraph(attributePaths = {"department"})
    List<Employee> findAll();
}

