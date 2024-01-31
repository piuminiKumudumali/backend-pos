package com.ijse.ijse103databasepos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.ijse103databasepos.dto.UserPwdDto;
import com.ijse.ijse103databasepos.entity.User;
import com.ijse.ijse103databasepos.service.UserService;
 
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    private ResponseEntity<List<User>> getAllUsers(){
       return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
    @PostMapping("/users")
    private ResponseEntity<User> createUser(@RequestBody User user){
       // return userService.createUser(user);
       try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
       } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
       }
    }

    @PutMapping("/users/{id}/change-password")
    private ResponseEntity<User> changeUserPassword(@PathVariable Long id,@RequestBody UserPwdDto userPwdDto){
        return ResponseEntity.ok().body(userService.changeUserPassword(id, userPwdDto));
    }
    
    
}
