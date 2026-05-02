package com.example.attendance.service;

import com.example.attendance.model.User;
import com.example.attendance.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {

        //return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<User> optionalUser = userRepository.findByUsername(username);

         if (optionalUser.isPresent()) {
             return optionalUser.get();
         } else {
             throw new UsernameNotFoundException("User not found");
         }
        }
}
