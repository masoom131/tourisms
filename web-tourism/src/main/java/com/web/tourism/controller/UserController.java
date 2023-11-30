package com.web.tourism.controller;

import com.web.tourism.payload.UserDto;
import com.web.tourism.reqres.ApiResponse;
import com.web.tourism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto addUser = userService.createUser(userDto);
        return ResponseEntity.ok(addUser);
    }

    @PutMapping("/up/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long userId) {
        UserDto updateUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/del/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse("User Deleted Successfully!!!", true));
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto> getByUserId(@PathVariable Long userId) {
        UserDto userById = userService.getByUserId(userId);
        return ResponseEntity.ok(userById);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getByUserId() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/ua/add")
    public ResponseEntity<UserDto> createUserAddress(@RequestBody UserDto userDto) {
        UserDto addUser = userService.createUser(userDto);
        return ResponseEntity.ok(addUser);
    }

    @PutMapping("/up/ua/{userId}")
    public ResponseEntity<UserDto> updateUserAddress(@RequestBody UserDto userDto, @PathVariable Long userId) {
        UserDto updateUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updateUser);
    }
}
