package com.beamofsoul.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {

	private static final long serialVersionUID = -300036193618708950L;

	private Long id;
	private String username;
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date birthday;
	private Boolean sex;
	private String address;
	//0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,1:正常,2:锁定,3:冻结
	private byte status;
//	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	
	public User() {}
	
	public User(Long id) {
		this.id = id;
	}
	
	public User(String username, String password, Date birthday, Boolean sex,
			String address) {
		super();
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.sex = sex;
		this.address = address;
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

	@NotEmpty(message="用户名不能为空")
	@Column(name = "username", unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty(message="密码不能为空")
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "birthday")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "sex")
	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "status")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", birthday=" + birthday + ", sex=" + sex
				+ ", address=" + address + ", status=" + status + "]";
	}
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
//	public Set<UserRole> getUserRoles() {
//		return userRoles;
//	}
//
//	public void setUserRoles(Set<UserRole> userRoles) {
//		this.userRoles = userRoles;
//	}
}
