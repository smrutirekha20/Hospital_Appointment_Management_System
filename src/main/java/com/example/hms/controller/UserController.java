package com.example.hms.controller;


import com.example.hms.RequestDto.LoginRequest;
import com.example.hms.RequestDto.UserRequest;
import com.example.hms.ResponseDto.UserResponse;
import com.example.hms.Service.UserService;
import com.example.hms.utility.AppResponseBuilder;
import com.example.hms.utility.ErrorStructure;
import com.example.hms.utility.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("${hospital.base_url}")
public class UserController {


    private final AppResponseBuilder appResponseBuilder;

    private final UserService userService;
    @Operation(description = "The end point can be used to save the data", responses =
            {
                    @ApiResponse(responseCode = "201", description = "User created"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })
    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody @Valid UserRequest
                                                                           userRequest) {
        UserResponse userResponse = userService.saveUser(userRequest);
        return appResponseBuilder.success(HttpStatus.CREATED, "User created", userResponse);
    }
    @Operation(description = "The end point can be used to login", responses =
            {
                    @ApiResponse(responseCode = "201", description = "user loggedIn"),
                    @ApiResponse(responseCode = "400", description = "invalid input", content = {
                            @Content(schema = @Schema(implementation = ErrorStructure.class))
                    })
            })

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<UserResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {
        UserResponse userResponse = userService.login(loginRequest);


        return appResponseBuilder.success(HttpStatus.OK, "logged in", userResponse);
    }
}
