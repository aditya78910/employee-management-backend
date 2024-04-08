package com.concertai.employeemanagement.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_generator")
    @SequenceGenerator(name = "department_generator", sequenceName = "department_seq", allocationSize = 1)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "department")
    @JsonBackReference
    private List<Employee> employees;
}
