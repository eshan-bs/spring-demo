package com.example.demo.service;

import com.example.demo.MyUserDetails;
import com.example.demo.exception.UserEmailOrNameNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserDetails loadUserByEmailOrName(String emailOrName) throws UserEmailOrNameNotFoundException {


        User user = userRepository.findUsersByEmailOrName(emailOrName)
                .orElseThrow(UserEmailOrNameNotFoundException::new);

        return new MyUserDetails(user);
        }


        /*

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("mojo")
                        .password("1234")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);

         */

    @Override
    public UserDetails loadUserByUsername(String name){
        return this.loadUserByEmailOrName(name);
    }

}
