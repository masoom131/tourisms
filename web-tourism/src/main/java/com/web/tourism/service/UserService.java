package com.web.tourism.service;


import com.web.tourism.entity.User;
import com.web.tourism.payload.UserDto;

import java.util.List;

public interface UserService {

    String registerUser(User user);
    String authUser(User user);
    String updatePassword(User user);

    String addUser(User user);
    String updateUser(User user, Long userId);
    String deleteUser(Long userId);
    String getUser(Long userId);
    String getAllUser();

    String addUserAddress(User user);
    String updateUserAddress(User user, Long userId);
    String deleteUserAddress(Long userId);
    String getUserAddress(Long userId);
    String getAllUserAddress();
}
