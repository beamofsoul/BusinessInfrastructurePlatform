package com.beamofsoul.springboot.dto;

import com.beamofsoul.springboot.entity.Permission;
import com.beamofsoul.springboot.entity.Role;

public class RolePermissionDTO {

	private Long roleId;
	private String roleName;
	private Long permissionId;
	private String permissionName;
	private String permissionAction;
	private String permissionUrl;
	private String permissionResourceType;
	private Long permissionParentId;
	private Boolean permissionAvailable;

	public RolePermissionDTO() {
		super();
	}

	public RolePermissionDTO(Long roleId, String roleName,
			Long permissionId, String permissionName,
			Boolean permissionAvailable, Long permissionParentId,
			String permissionResourceType, String permissionUrl,
			String permissionAction) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.permissionAvailable = permissionAvailable;
		this.permissionParentId = permissionParentId;
		this.permissionResourceType = permissionResourceType;
		this.permissionUrl = permissionUrl;
		this.permissionAction = permissionAction;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionAction() {
		return permissionAction;
	}

	public void setPermissionAction(String permissionAction) {
		this.permissionAction = permissionAction;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public String getPermissionResourceType() {
		return permissionResourceType;
	}

	public void setPermissionResourceType(String permissionResourceType) {
		this.permissionResourceType = permissionResourceType;
	}

	public Long getPermissionParentId() {
		return permissionParentId;
	}

	public void setPermissionParentId(Long permissionParentId) {
		this.permissionParentId = permissionParentId;
	}

	public Boolean getPermissionAvailable() {
		return permissionAvailable;
	}

	public void setPermissionAvailable(Boolean permissionAvailable) {
		this.permissionAvailable = permissionAvailable;
	}

	public Role convertToRole() {
		return new Role(roleId, roleName);
	}

	public Permission convertToPermission() {
		return new Permission(permissionId, permissionName, permissionAction,
				permissionUrl, permissionResourceType, permissionParentId,
				permissionAvailable);
	}
}
