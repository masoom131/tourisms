package com.web.tourism.controller;

import com.web.tourism.entity.User;
import com.web.tourism.service.UserService;
import com.web.tourism.util.WebTourismUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private WebTourismUtil webTourismUtil;

    /* User API Controller */
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String addUser = userService.addUser(user);
        return webTourismUtil.responseStatus(addUser);
    }

    @PutMapping("/up/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable Long userId) {
        String updateUser = userService.updateUser(user, userId);
        return webTourismUtil.responseStatus(updateUser);
    }

    @DeleteMapping("/del/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String deleteUser = userService.deleteUser(userId);
        return webTourismUtil.responseStatus(deleteUser);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<String> getUser(@PathVariable Long userId) {
        String fetchUser = userService.getUser(userId);
        return webTourismUtil.responseStatus(fetchUser);
    }

    @GetMapping("/get/all")
    public ResponseEntity<String> getAllUser() {
        String fetchAllUser = userService.getAllUser();
        return webTourismUtil.responseStatus(fetchAllUser);
    }


    /* User With Address API Controller */
    @PostMapping("/ua/add")
    public ResponseEntity<String> addUserAddress(@RequestBody User user) {
        String addUserAddress = userService.addUserAddress(user);
        return webTourismUtil.responseStatus(addUserAddress);
    }

    @PutMapping("/ua/up/{userId}")
    public ResponseEntity<String> updateUserAddress(@RequestBody User user, @PathVariable Long userId) {
        String updateUserAddress = userService.updateUserAddress(user, userId);
        return webTourismUtil.responseStatus(updateUserAddress);
    }

    @DeleteMapping("/ua/del/{userId}")
    public ResponseEntity<String> deleteUserAddress(@PathVariable Long userId) {
        String deleteUserAddress = userService.deleteUserAddress(userId);
        return webTourismUtil.responseStatus(deleteUserAddress);
    }

    @GetMapping("/ua/get/{userId}")
    public ResponseEntity<String> getUserAddress(@PathVariable Long userId) {
        String fetchUserAddress = userService.getUserAddress(userId);
        return webTourismUtil.responseStatus(fetchUserAddress);
    }

    @GetMapping("/ua/get/all")
    public ResponseEntity<String> getAllUserAddress() {
        String fetchAllUserAddress = userService.getAllUserAddress();
        return webTourismUtil.responseStatus(fetchAllUserAddress);
    }
}
