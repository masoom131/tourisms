package com.web.tourism.service.impl;

import com.web.tourism.entity.Address;
import com.web.tourism.entity.User;
import com.web.tourism.exception.ResourceNotFoundException;
import com.web.tourism.repository.AddressRepository;
import com.web.tourism.repository.UserRepository;
import com.web.tourism.service.UserService;
import com.web.tourism.util.WebTourismUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WebTourismUtil webTourismUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String registerUser(User user) {
        return null;
    }

    @Override
    public String authUser(User user) {
        return null;
    }

    @Override
    public String updatePassword(User user) {
        return null;
    }

    /* User Implementations */
    @Override
    public String addUser(User user) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try {
            boolean existsByUserEmail = userRepository.existsByUserEmail(user.getUserEmail());
            if (existsByUserEmail) {
                message_en = "EmailAddress Already Exists";
            } else {
                User addUser = userRepository.save(user);
                message_en = "User Added Successfully.";
            }
        }catch (Exception e){
            message_en = "Exception While Adding User!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String updateUser(User user, Long userId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            User updateUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
            updateUser.setUserName(user.getUserName());
            updateUser.setUserEmail(user.getUserEmail());
            updateUser.setUserPhone(user.getUserPhone());
            updateUser.setUserGender(user.getUserGender());
            updateUser.setModifiedDate(new Date());
            userRepository.save(updateUser);
            message_en = "User Updated Successfully.";
        }catch (Exception e){
            message_en = "Exception While Update User!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String deleteUser(Long userId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            User user = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
            userRepository.delete(user);
            message_en = "User Deleted Successfully!";
        }catch (Exception e){
            message_en = "Exception While Delete User!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getUser(Long userId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            User user = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
            Map<String, User> map = new HashMap();
            map.put("user", user);
            jsonResponse.put("data",map);
            message_en = "User Find By Id Successfully!";
        }catch (Exception e){
            message_en = "Exception While Fetching User!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getAllUser() {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            List<User> userList = userRepository.findAll();
            for (User user : userList){
                Map<String, User> map = new HashMap();
                map.put("user", user);
                jsonArray.put(map);
                jsonResponse.put("data", jsonArray);
            }
            message_en = "Records fetching successfully!!!";
        }catch (Exception e){
            message_en = "Exception While Fetching All User!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    /* User With Address Implementations */
    @Override
    public String addUserAddress(User user) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            boolean existsUserEmail = userRepository.existsByUserEmail(user.getUserEmail());
            if(existsUserEmail){
                message_en = "EmailAddress Already Exists";
            }else {
                User addUser = userRepository.save(user);
                message_en = "User Added Successfully.";
            }
        }catch (Exception e){
            message_en = "Exception While Adding UserAddress!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String updateUserAddress(User user, Long userId) {
        System.out.println("updateUserAddress serviceImpl Called");
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            User updateUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
            Address updateAddress = addressRepository.findById(updateUser.getAddress().getAddressId())
                    .orElseThrow(()->new ResourceNotFoundException("Address","Address Id", updateUser.getAddress().getAddressId()));
            updateUser.setUserName(user.getUserName());
            updateUser.setUserEmail(user.getUserEmail());
            updateUser.setUserPhone(user.getUserPhone());
            updateUser.setUserGender(user.getUserGender());
            updateUser.setUserPassword(user.getUserPassword());
            updateUser.setModifiedDate(new Date());
            updateAddress.setUserStreet(user.getAddress().getUserStreet());
            updateAddress.setUserState(user.getAddress().getUserState());
            updateAddress.setUserCity(user.getAddress().getUserCity());
            updateAddress.setUserCountry(user.getAddress().getUserCountry());
            updateAddress.setZipCode(user.getAddress().getZipCode());
            updateAddress.setModifiedDate(new Date());
            userRepository.save(updateUser);
            message_en = "User Updated Successfully.";
        }catch (Exception e){
            message_en = "Exception While Update User!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String deleteUserAddress(Long userId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            User user = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
            userRepository.delete(user);
            message_en = "User with Address Deleted Successfully!";
        }catch (Exception e){
            message_en = "Exception While Delete Record!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getUserAddress(Long userId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            User user = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","User Id", userId));
            Map<String, User> map = new HashMap();
            map.put("user", user);
            jsonResponse.put("data", map);
            message_en = "User Find By Id Successfully!";
        }catch (Exception e){
            message_en = "Exception While Fetching Record!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getAllUserAddress() {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            List<User> users = userRepository.findAll();
            for (User user : users){
                Map<String, User> map = new HashMap();
                map.put("user", user);
                jsonArray.put(map);
                jsonResponse.put("data", jsonArray);
            }
            message_en = "Records fetching successfully!!!";
        }catch (Exception e){
            message_en = "Exception While Fetching All Records!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }


}
