package com.example.hms.Service;

import com.example.hms.Entity.User;
import com.example.hms.Exception.UserNotFoundException;
import com.example.hms.Mapper.UserMapper;
import com.example.hms.Repository.UserRepository;
import com.example.hms.RequestDto.LoginRequest;
import com.example.hms.RequestDto.UserRequest;
import com.example.hms.ResponseDto.UserResponse;
import com.example.hms.Security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public UserResponse saveUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = userMapper.mapToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        return userMapper.mapToUserResponse(user);
    }


//    public UserResponse login(LoginRequest loginRequest) {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
//        Authentication authentication = authenticationManager.authenticate(token);
//        if (authentication.isAuthenticated()) {
//            User user = userRepository
//                    .findByEmail(loginRequest.getEmail())
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found "));
//
//
//            UserResponse userResponse = new UserResponse();
//
//            userResponse.setId(user.getId());
//            userResponse.setEmail(user.getEmail());
//            userResponse.setUserRole(user.getUserRole());
//
//            return userResponse;
//        } else {
//            throw new UserNotFoundException("Failed to found username");
//        }
//
//    }

    public UserResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);

        if (authentication.isAuthenticated()) {
            User user = userRepository
                    .findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String jwtToken = jwtService.generateJwt(user.getEmail(), user.getUserRole().name());

            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setUserRole(user.getUserRole());
            userResponse.setToken(jwtToken);

            return userResponse;
        } else {
            throw new BadCredentialsException("Invalid login credentials");
        }
    }

}



