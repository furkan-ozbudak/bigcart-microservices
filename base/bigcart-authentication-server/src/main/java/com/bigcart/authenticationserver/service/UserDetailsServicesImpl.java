package com.bigcart.authenticationserver.service;

import com.bigcart.authenticationserver.model.AuthUserDetail;
import com.bigcart.authenticationserver.model.User;
import com.bigcart.authenticationserver.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
