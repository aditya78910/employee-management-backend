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
public class AccessControlPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String permission;

    @OneToMany(mappedBy = "accessControlPermission")
    @JsonBackReference
    private List<AccessControlUserResourcePermissionMapping> userResourcePermissionMappingList;
}
