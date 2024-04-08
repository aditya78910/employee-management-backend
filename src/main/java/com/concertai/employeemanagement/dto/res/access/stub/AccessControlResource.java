package com.concertai.employeemanagement.dto.res.access.stub;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessControlResource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String resourceId;
    private String resourceType;
    private String resourceName;

    @OneToMany(mappedBy = "accessControlResource")
    @JsonBackReference
    private List<AccessControlUserResourcePermissionMapping> userResourcePermissionMappings;
}
