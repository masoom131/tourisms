package com.web.tourism.service;

import com.web.tourism.entity.Role;
import com.web.tourism.payload.RoleDto;

import java.util.List;

public interface RoleService {

    String addRole(Role role);

    String updateRole(Role role, Long roleId);

    String deleteRole(Long roleId);

    String getRole(Long roleId);

    String getAllRole();
}
