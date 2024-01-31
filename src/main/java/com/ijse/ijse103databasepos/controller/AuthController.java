package com.ijse.ijse103databasepos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.ijse103databasepos.dto.LoginDto;
import com.ijse.ijse103databasepos.entity.User;
import com.ijse.ijse103databasepos.repository.UserRepository;
import com.ijse.ijse103databasepos.security.jwt.JwtUtils;
import com.ijse.ijse103databasepos.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/auth/login")
    public String login(){
        return "Login route is working without auth";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> userRegister(@RequestBody User user){
        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body("This username already exists");
        }
        if(userRepository.existsByUsername(user.getEmail())){
            return ResponseEntity.badRequest().body("This email already exists");
        }

        User newUser=new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return ResponseEntity.ok(userService.createUser(newUser));

    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        Authentication authentication=authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(jwt);
    }

    
}
