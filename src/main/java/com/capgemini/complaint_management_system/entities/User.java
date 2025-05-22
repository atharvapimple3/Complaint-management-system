package com.capgemini.complaint_management_system.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "User_table")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@NotBlank(message = "Name cannot be blank")
	private String name;

	@Email(message = "Enter valid email")
	private String email;

	@NotBlank(message = "Enter valid password")
	private String password;

	// @Size(min = 10, max = 10, message = "Enter valid number")
	private Long phone;

	@NotBlank(message = "Enter valid user")
	private String userType;
	

	public User() {

	}

	public User(@NotBlank(message = "Name cannot be blank") String name,
			@Email(message = "Enter valid email") String email,
			@NotBlank(message = "Enter valid password") String password,
			@Size(min = 10, max = 10, message = "Enter valid number") Long phone,
			@NotBlank(message = "Enter valid user") String userType) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userType = userType;
	}

	public User(Integer userId, @NotBlank(message = "Name cannot be blank") String name,
			@Email(message = "Enter valid email") String email,
			@NotBlank(message = "Enter valid password") String password,
			@Size(min = 10, max = 10, message = "Enter valid number") Long phone,
			@NotBlank(message = "Enter valid user") String userType) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userType = userType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, name, password, phone, userId, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(phone, other.phone)
				&& Objects.equals(userId, other.userId) && Objects.equals(userType, other.userType);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", userType=" + userType + "]";
	}

}
