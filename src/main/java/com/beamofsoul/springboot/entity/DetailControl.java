package com.beamofsoul.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_DETAIL_CONTROL")
public class DetailControl implements Serializable {

	private static final long serialVersionUID = -6722830563824341150L;
	
	private Long id;
	private Long roleId;
	private String entityClass;
	private String unavailableColumns;
	private String filterRules;
	//priority的值越小,优先级越高
	private Integer priority;
	private Boolean enabled; 
	
	public DetailControl() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "role_id")
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "entity_class")
	public String getEntityClass() {
		return this.entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	@Column(name = "unavailable_columns")
	public String getUnavailableColumns() {
		return unavailableColumns;
	}

	public void setUnavailableColumns(String unavailableColumns) {
		this.unavailableColumns = unavailableColumns;
	}

	@Column(name = "filter_rules")
	public String getFilterRules() {
		return filterRules;
	}

	public void setFilterRules(String filterRules) {
		this.filterRules = filterRules;
	}

	@Column(name = "priority")
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Column(name = "enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
