package com.web.tourism.service;

import com.web.tourism.payload.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto createRole(RoleDto roleDto);

    RoleDto updateRole(RoleDto roleDto, Long roleId);

    void deleteRole(Long roleId);

    RoleDto getRole(Long roleId);

    List<RoleDto> getRoles();
}
