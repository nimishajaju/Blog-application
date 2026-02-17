package com.project.blog.application.Security;

import com.project.blog.application.entity.User;
import com.project.blog.application.exceptions.ResourceNotFoundException;
import com.project.blog.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=  userRepository.findByEmail(username).orElse(null);
       if(user==null){
           throw new UsernameNotFoundException("User not found");
       }

        return user;
    }
}
