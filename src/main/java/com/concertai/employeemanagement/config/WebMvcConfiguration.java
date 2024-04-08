package com.concertai.employeemanagement.config;

import com.concertai.employeemanagement.resolver.UserProfileArguementResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    UserProfileArguementResolver userProfileArguementResolver;
    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userProfileArguementResolver);
    }
}
