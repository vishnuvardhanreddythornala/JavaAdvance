package com.cap.BookStroreRest.Service;
import com.cap.BookStroreRest.Entity.User;
import com.cap.BookStroreRest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// Loads user from database for Spring Security
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Spring Security calls this method to get user info
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Return user info in Spring Security format
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),        // email as username
                user.getPassword(),     // hashed password
                new ArrayList<>()       // no roles for now
        );
    }
}

