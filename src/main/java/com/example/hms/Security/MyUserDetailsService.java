//package com.example.hms.Security;
//
//import com.example.hms.Entity.User;
//import com.example.hms.Repository.AdminRepository;
//import com.example.hms.Repository.PatientRepository;
//import com.example.hms.Repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.hibernate.Hibernate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class MyUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        Hibernate.initialize(user);
//        return new UserDetailsImpl(user);
//    }
//}

