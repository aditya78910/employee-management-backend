package com.concertai.employeemanagement.security.authorization.service;

import com.concertai.employeemanagement.dto.req.access.UserResourcePermissionRequestDTO;
import com.concertai.employeemanagement.dto.res.access.stub.UserResourcePermissionsResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccessControlService {

    AccessControlWebService accessControlWebService;
    public UserResourcePermissionsResponseDTO getPermissionsForUserAndResource(UserResourcePermissionRequestDTO userResourcePermissionRequestDTO){
        return accessControlWebService.getPermissionsForUserAndResource(userResourcePermissionRequestDTO);
    }
}
