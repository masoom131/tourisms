package com.web.tourism.service.impl;

import com.web.tourism.entity.Role;
import com.web.tourism.exception.ResourceNotFoundException;
import com.web.tourism.payload.RoleDto;
import com.web.tourism.repository.RoleRepository;
import com.web.tourism.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        Role addRole = roleRepository.save(role);
        return modelMapper.map(addRole, RoleDto.class);
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()->new ResourceNotFoundException("Role","Role Id", roleId));
        role.setRoleName(roleDto.getRoleName());

        Role updateRole = roleRepository.save(role);
        return modelMapper.map(updateRole, RoleDto.class);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()->new ResourceNotFoundException("Role","Role Id", roleId));
        roleRepository.delete(role);
    }

    @Override
    public RoleDto getRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()->new ResourceNotFoundException("Role","Role Id", roleId));
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map((role)-> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
    }
}
