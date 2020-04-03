package com.comapny.Project.auth;

import com.comapny.Project.user.User;
import com.comapny.Project.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUserName(username);
        user.orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", username)));
        return user.map(ApplicationUser::new).get();

    }
}
