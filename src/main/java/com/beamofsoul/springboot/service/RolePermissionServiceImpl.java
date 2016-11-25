package com.beamofsoul.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beamofsoul.springboot.dto.RolePermissionDTO;
import com.beamofsoul.springboot.repository.RolePermissionRepository;

@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Override
	public List<RolePermissionDTO> findAllRolePermissionMapping() {
		return rolePermissionRepository.findAllRolePermissionMapping();
	}
}
