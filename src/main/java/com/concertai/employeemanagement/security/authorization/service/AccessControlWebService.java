package com.concertai.employeemanagement.security.authorization.service;

import com.concertai.employeemanagement.dto.req.access.UserResourcePermissionRequestDTO;
import com.concertai.employeemanagement.dto.res.access.stub.UserResourcePermissionsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AccessControlWebService {

    @Value("${application.access-control-service.url}")
    String accessControlBaseUrl;

    @Value("${application.access-control-service.permissionsPath}")
    private String accessControlPermissionsPath;


    @Autowired
    RestClient restClient;

    public UserResourcePermissionsResponseDTO getPermissionsForUserAndResource
            (UserResourcePermissionRequestDTO userResourcePermissionRequestDTO){

        return restClient.
                post()
                .uri(accessControlBaseUrl + accessControlPermissionsPath)
                .body(userResourcePermissionRequestDTO)
                .retrieve()
                .body(UserResourcePermissionsResponseDTO.class);

    }


}
