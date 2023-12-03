package com.web.tourism.controller;

import com.web.tourism.entity.Role;
import com.web.tourism.service.RoleService;
import com.web.tourism.util.WebTourismUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/role")
public class RoleController {

    @Autowired
    private WebTourismUtil webTourismUtil;
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<String> addRole(@RequestBody Role role) {
        String addRole = roleService.addRole(role);
        return webTourismUtil.responseStatus(addRole);
    }

    @PutMapping("/up/{roleId}")
    public ResponseEntity<String> updateRole(@RequestBody Role role, @PathVariable Long roleId) {
        String updateRole = roleService.updateRole(role, roleId);
        return webTourismUtil.responseStatus(updateRole);
    }

    @DeleteMapping("/del/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable Long roleId) {
        String deleteRole = roleService.deleteRole(roleId);
        return webTourismUtil.responseStatus(deleteRole);
    }

    @GetMapping("/get/{roleId}")
    public ResponseEntity<String> getRole(@PathVariable Long roleId) {
        String fetchRole = roleService.getRole(roleId);
        return webTourismUtil.responseStatus(fetchRole);
    }

    @GetMapping("/get/all")
    public ResponseEntity<String> getAllRole() {
        String fetchAllRole = roleService.getAllRole();
        return webTourismUtil.responseStatus(fetchAllRole);
    }

}
