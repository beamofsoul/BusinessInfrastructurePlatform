package com.beamofsoul.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PERMISSION")
public class Permission implements Serializable {

	private static final long serialVersionUID = -7700581193909669401L;
	
	private Long id;
	private String name;
	private String action;
	private String url;
	private String resourceType;
	private Long parentId;
	private Boolean available = Boolean.FALSE;
	
	public Permission() {}
	
	public Permission(Long id, String name, String action, String url,
			String resourceType, Long parentId, Boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.action = action;
		this.url = url;
		this.resourceType = resourceType;
		this.parentId = parentId;
		this.available = available;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "action")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "resourceType", columnDefinition = "enum('menu','button')")
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "parentId")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "available")
	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
