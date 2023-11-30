package com.web.tourism.service.impl;

import com.web.tourism.entity.User;
import com.web.tourism.exception.ResourceNotFoundException;
import com.web.tourism.payload.UserDto;
import com.web.tourism.repository.UserRepository;
import com.web.tourism.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto registerUser() {
        return null;
    }

    @Override
    public UserDto authUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User addUser = userRepository.save(user);
        return modelMapper.map(addUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
        user.setUserName(userDto.getUserName());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserPhone(userDto.getUserPhone());
        user.setUserGender(userDto.getUserGender());
        user.setUserPassword(userDto.getUserPassword());

        User updateUser = userRepository.save(user);
        return modelMapper.map(updateUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
         userRepository.delete(user);
    }

    @Override
    public UserDto getByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user)-> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

}
