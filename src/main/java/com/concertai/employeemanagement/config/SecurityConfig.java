package com.concertai.employeemanagement.config;

import com.concertai.employeemanagement.security.authorization.permission.AccessPermissionEvaluator;
import com.concertai.employeemanagement.security.converter.TokenConverter;
import lombok.AllArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.authorization.method.PreAuthorizeAuthorizationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = false)
public class SecurityConfig {

    CorsConfigProperties corsConfigProperties;

    @Autowired
    TokenConverter tokenConverter;

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    Advisor preAuthorizeAuthorizationMethodIntercep(AccessPermissionEvaluator customPermissionEvaluator) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(customPermissionEvaluator);
        expressionHandler.setApplicationContext(applicationContext);
        PreAuthorizeAuthorizationManager authorizationManager = new PreAuthorizeAuthorizationManager();
        authorizationManager.setExpressionHandler(expressionHandler);

        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize(authorizationManager);
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        var corsConfigSource = new UrlBasedCorsConfigurationSource();
        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(corsConfigProperties.getAllowedOrigins());
        corsConfiguration.setAllowedMethods(corsConfigProperties.getAllowedMethods());
        corsConfiguration.setAllowCredentials(corsConfigProperties.isAllowedCredentials());
        corsConfiguration.setAllowedHeaders(corsConfigProperties.getAllowedHeaders());
        corsConfigSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigSource;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.
                csrf(AbstractHttpConfigurer::disable)
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .anyRequest().authenticated())
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                        httpSecurityOAuth2ResourceServerConfigurer
                                //.jwt(c -> c.)
                                .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(tokenConverter))
                )
                .build();
    }

}
