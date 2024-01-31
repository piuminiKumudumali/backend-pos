package com.ijse.ijse103databasepos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.ijse103databasepos.dto.UserPwdDto;
import com.ijse.ijse103databasepos.entity.User;

@Service
public interface UserService {
     List<User>getAllUsers();
     User createUser(User user);
     User getUserById(Long id);
     User changeUserPassword(Long id, UserPwdDto userPwdDto);
}
