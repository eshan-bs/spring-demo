package com.example.demo.service;

import com.example.demo.exception.IncorrectEmailOrPassword;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.MyUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserDetails loadUserByEmailOrName(String emailOrName) throws IncorrectEmailOrPassword{

//        MyUser myUser = userRepository.findUsersByEmailOrName(emailOrName)
//                .orElseThrow(UserEmailOrNameNotFoundException::new);
//                ).collect(Collectors.toList()); // (1)

        MyUser myUser;

        if (isValidEmail(emailOrName)){
            myUser = userRepository.findMyUserByEmail(emailOrName)
                    .orElseThrow(IncorrectEmailOrPassword::new);
        }
        else {
            myUser = userRepository.findMyUserByName(emailOrName)
                    .orElseThrow(IncorrectEmailOrPassword::new);
        }

        List<SimpleGrantedAuthority> grantedAuthorities =
                myUser.getAuthorities().stream()
                .map(
                        authority -> new SimpleGrantedAuthority(authority.toString())
                ).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(myUser.getName(), myUser.getPassword(), grantedAuthorities);
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
        try{
            return this.loadUserByEmailOrName(name);
        }
        catch (Exception errorMessage){
            log.info(errorMessage.toString());
        }
        return null;
    }

    public boolean isValidEmail(String emailAddress) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}
