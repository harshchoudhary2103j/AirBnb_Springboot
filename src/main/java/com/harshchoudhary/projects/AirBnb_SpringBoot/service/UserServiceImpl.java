package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.User;
import com.harshchoudhary.projects.AirBnb_SpringBoot.exception.ResourceNotFoundException;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not found with id: "+id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }
}
