package com.concertai.employeemanagement.dto.res.access.stub;

import lombok.Data;

@Data
public class AccessControlUserResourcePermissionMapping {
    private Long id;
    private Long userId;
    private AccessControlResource accessControlResource;
    private AccessControlPermission accessControlPermission;
}
