package com.web.tourism.service;


import com.web.tourism.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser();

    UserDto authUser(UserDto userDto);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    void deleteUser(Long userId);

    UserDto getByUserId(Long userId);

    List<UserDto> getAllUsers();

}
