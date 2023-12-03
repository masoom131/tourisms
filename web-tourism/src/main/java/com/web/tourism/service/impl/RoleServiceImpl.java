package com.web.tourism.service.impl;

import com.web.tourism.entity.Role;
import com.web.tourism.exception.ResourceNotFoundException;
import com.web.tourism.payload.RoleDto;
import com.web.tourism.repository.RoleRepository;
import com.web.tourism.service.RoleService;
import com.web.tourism.util.WebTourismUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private WebTourismUtil webTourismUtil;

    @Override
    public String addRole(Role role) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try {
            Role addRole = roleRepository.save(role);
            message_en = "Role Added Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While Adding Role!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String updateRole(Role role, Long roleId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            Role updateRole = roleRepository.findById(roleId)
                    .orElseThrow(()->new ResourceNotFoundException("Role","Role Id", roleId));
            updateRole.setRoleName(role.getRoleName());
            updateRole.setRoleType(role.getRoleType());
            updateRole.setModifiedDate(new Date());
            roleRepository.save(updateRole);
            message_en = "Role Updated Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While updating Role!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String deleteRole(Long roleId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            Role deleteRole = roleRepository.findById(roleId)
                    .orElseThrow(()->new ResourceNotFoundException("Role","Role Id", roleId));
            roleRepository.delete(deleteRole);
            message_en = "Role Deleted Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While Adding Role!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getRole(Long roleId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(()->new ResourceNotFoundException("Role","Role Id", roleId));
            Map<String, Role> map = new HashMap<>();
            map.put("role", role);
            jsonResponse.put("data", map);
            message_en = "Role fetched Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While fetching Role!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getAllRole() {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            List<Role> roles = roleRepository.findAll();
            for (Role role : roles){
                Map<String, Role> map = new HashMap<>();
                map.put("role", role);
                jsonArray.put(map);
                jsonResponse.put("data", jsonArray);
            }
            message_en = "Role fetched Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While fetching Role!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

}
