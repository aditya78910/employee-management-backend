package com.concertai.employeemanagement.dto.res.access.stub;

import lombok.Data;

import java.util.List;

@Data
public class UserResourcePermissionsResponseDTO {
    private List<AccessControlUserResourcePermissionMapping> accessControlUserResourcePermissionMappingList;
}