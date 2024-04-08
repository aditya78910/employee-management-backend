package com.concertai.employeemanagement.dto.req.access;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResourcePermissionRequestDTO {
    private Long userId;
    private String resourceId;
    private String resourceName;
}
