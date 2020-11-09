//package com.bigcart.apigateway.config;
//
//import com.bigcart.apigateway.dto.EmployeeDTO;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//@Component
//public class UserManagmentAuthenticationProvider implements AuthenticationProvider {
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String userName = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        System.out.println(userName);
//        System.out.println(password);
//
//        URI uri = null;
//        try {
//            uri = new URI("http://localhost:9988/employee/login");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        class temp {
//            public String userName;
//            public String password;
//            temp(String user, String pass)
//            {
//                userName = user;
//                password = pass;
//            }
//        };
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity entity = restTemplate.postForEntity(uri, new temp(userName, password), EmployeeDTO.class);
//        if(entity.getStatusCode() == HttpStatus.OK) {
//            System.out.println(entity);
//            authentication.setAuthenticated(true);
//            return authentication;
//        }
//
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
