package com.example.hms.Security;

import com.example.hms.Entity.User;
import com.example.hms.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthUtil {
private final UserRepository userRepository;

        public boolean isUserAuthenticated(){
            return this.getAuthentication()!= null;
        }
        private Authentication getAuthentication(){

            return SecurityContextHolder.getContext().getAuthentication();
        }

        public String getCurrentUsername(){

            return this.getAuthentication().getName();
        }

        public User getCurrentUser(){
            return userRepository.findByEmail(this.getCurrentUsername())
                    .orElseThrow(()->new UsernameNotFoundException("Failed to found user"));
        }
}
