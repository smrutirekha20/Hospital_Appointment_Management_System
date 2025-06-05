package com.example.hms.Service;

import com.example.hms.Entity.User;
import com.example.hms.Mapper.UserMapper;
import com.example.hms.Repository.UserRepository;
import com.example.hms.RequestDto.LoginRequest;
import com.example.hms.RequestDto.UserRequest;
import com.example.hms.ResponseDto.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

   private final UserRepository userRepository;
   private final UserMapper userMapper;
   private final PasswordEncoder passwordEncoder;
   private final AuthenticationManager authenticationManager;

   public UserResponse saveUser(UserRequest request){
      if (userRepository.existsByEmail(request.getEmail())) {
         throw new IllegalArgumentException("Email already exists");
      }

      User user = userMapper.mapToUser(request);
      user.setPassword(passwordEncoder.encode(request.getPassword())); // encrypting password
      user = userRepository.save(user);

      return userMapper.mapToUserResponse(user);

   }
   public UserResponse login(LoginRequest request){
      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
              request.getEmail(),
              request.getPassword()
      );

      Authentication authentication = authenticationManager.authenticate(token);
      if (authentication.isAuthenticated()) {
         User user = userRepository
                 .findByEmail(request.getEmail())
                 .orElseThrow(() -> new UsernameNotFoundException("User not found "));


         UserResponse userResponse = new UserResponse();

         userResponse.setId(user.getId());
         userResponse.setEmail(user.getEmail());
         userResponse.setUserRole(user.getUserRole());

         return userResponse;
      } else {
         throw new UsernameNotFoundException("Failed to found username");
      }
   }
}
