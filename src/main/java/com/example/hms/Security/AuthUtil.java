package com.example.hms.Security;


import com.example.hms.Entity.User;
import com.example.hms.Repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthUtil {

    private final UserRepository userRepository;

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAuthenticated() {
        return this.getAuthentication()!=null;

    }
    public String getCurrentUsername() {
        return this.getAuthentication().getName();
    }
    public User getCurrentUser() {
        return userRepository.findByEmail(this.getCurrentUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
