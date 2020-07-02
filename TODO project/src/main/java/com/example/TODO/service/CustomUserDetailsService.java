package com.example.TODO.service;

import com.example.TODO.model.CustomUserDetails;
import com.example.TODO.model.User;
import com.example.TODO.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser =userRepository.findByUsername(s);

        optionalUser
                .orElseThrow(() ->new UsernameNotFoundException("User name is  not found"));
        return optionalUser
                .map(CustomUserDetails::new).get();
    }
}
