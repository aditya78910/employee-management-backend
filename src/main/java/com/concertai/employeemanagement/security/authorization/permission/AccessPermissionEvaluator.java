package com.concertai.employeemanagement.security.authorization.permission;

import com.concertai.employeemanagement.dto.req.access.UserResourcePermissionRequestDTO;
import com.concertai.employeemanagement.dto.res.access.stub.UserResourcePermissionsResponseDTO;
import com.concertai.employeemanagement.security.authorization.service.AccessControlService;
import com.concertai.employeemanagement.security.token.CustomJwtToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class AccessPermissionEvaluator implements PermissionEvaluator {

    AccessControlService accessControlService;

    //Not USED
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        Long userId = ((CustomJwtToken)authentication).getUserProfile().getUserId();
        List<String> allowedPermissions;
        if(permission instanceof List){
            allowedPermissions = (List<String>)permission;
        }
        else {
            String permissionStr = (String)permission;
            allowedPermissions = Collections.singletonList(permissionStr);
        }

        String targetIdStr = null;

        if(!ObjectUtils.isEmpty(targetId))
            targetIdStr = String.valueOf(targetId);

        UserResourcePermissionRequestDTO permissionsRequest = UserResourcePermissionRequestDTO.builder()
                .userId(userId)
                .resourceId(targetIdStr)
                .resourceName(targetType)
                .build();

        UserResourcePermissionsResponseDTO permissionsForUserAndResource =
                accessControlService.getPermissionsForUserAndResource(permissionsRequest);

        // If any permission matches , grant the access by returning true
        return permissionsForUserAndResource.getAccessControlUserResourcePermissionMappingList().stream()
                .map(userResourcePermission-> userResourcePermission.getAccessControlPermission().getPermission())
                .anyMatch(allowedPermissions::contains);

    }

}