package com.bigcart.apigateway.service;

import com.bigcart.apigateway.model.AuthUserDetail;
import com.bigcart.apigateway.model.User;
import com.bigcart.apigateway.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Service("userDetailsService")
public class UserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optional = userDetailsRepository.findByUsername(name);
        optional.orElseThrow(()-> new UsernameNotFoundException("The username and password are wrong"));

        UserDetails userDetails = new AuthUserDetail(optional.get());

        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }

//    public ResponseEntity checkUser(String name, String password) {
//
//        String uri = "http://127.0.0.1:9988/";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        HttpEntity<User> entity = new HttpEntity<>(user,headers);
//
//        ResponseEntity userResponseEntity = restTemplate.exchange(uri, HttpMethod.POST,entity,User.class);
//
//        return new ResponseEntity(userResponseEntity,HttpStatus.OK);
//    }

}
