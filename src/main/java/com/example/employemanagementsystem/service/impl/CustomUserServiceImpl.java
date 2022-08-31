package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.model.entity.UserEntity;
import com.example.employemanagementsystem.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username " + username + " is not found."));

        UserDetails userDetails = userDetailsMapping(userEntity);

        return userDetails;
    }

    private static UserDetails userDetailsMapping(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .collect(Collectors.toList());

        return new User(userEntity.getUserName(), userEntity.getPassword(), authorities);
    }
}
