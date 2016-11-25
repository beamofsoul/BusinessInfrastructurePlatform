package com.beamofsoul.springboot.service;

import java.util.List;

import com.beamofsoul.springboot.dto.RolePermissionDTO;

public interface RolePermissionService {

	List<RolePermissionDTO> findAllRolePermissionMapping();
}
