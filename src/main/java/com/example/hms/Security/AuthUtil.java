//package com.example.hms.Security;
//
//
//import com.example.hms.Entity.User;
//import com.example.hms.Repository.UserRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//
//public class AuthUtil {
//
//    private UserRepository userRepository;
//
//    @Autowired
//    public AuthUtil(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    private Authentication getAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//
//    public boolean isUserAuthenticated() {
//        Authentication auth = getAuthentication();
//        return auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
//    }
//
//    public String getCurrentUsername() {
//        Authentication auth = getAuthentication();
//        if (auth != null) return auth.getName();
//        throw new UsernameNotFoundException("No authenticated user");
//    }
//
//    public User getCurrentUser() {
//        return userRepository.findByEmail(getCurrentUsername())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//
//    // Flexible generic casting
//    public <T extends User> T getCurrentUserAs(Class<T> clazz) {
//        User user = getCurrentUser();
//        if (clazz.isInstance(user)) return clazz.cast(user);
//        throw new AccessDeniedException("Current user is not of type: " + clazz.getSimpleName());
//    }
//}
