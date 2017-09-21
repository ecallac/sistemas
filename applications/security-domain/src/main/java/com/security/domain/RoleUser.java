//package com.security.domain;
//
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "roleuser")
//public class RoleUser {
////	
////	@Id
////	@Column(name = "id")
////	@GeneratedValue(strategy=GenerationType.AUTO)
////	private Long id;
////	
//	@ManyToOne
//	@JoinColumn(name="user_Id")
//	private User user;
//	
//	@ManyToOne
//	@JoinColumn(name="role_Id")
//	private Role role;
//
//	/**
//	 * @return the user
//	 */
//	public User getUser() {
//		return user;
//	}
//
//	/**
//	 * @param user the user to set
//	 */
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	/**
//	 * @return the role
//	 */
//	public Role getRole() {
//		return role;
//	}
//
//	/**
//	 * @param role the role to set
//	 */
//	public void setRole(Role role) {
//		this.role = role;
//	}
//	
//}
