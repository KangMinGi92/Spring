package com.bs.helloboot.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="member")
public class MemberDto implements UserDetails {
	@Id
	@Column(name="userid")
	private String userId;
	private String password;
	@Column(name="username")
	private String name;
	private int age;
	private String gender;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	@Column(name="enrolldate")
	private Date enrollDate;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth=new ArrayList();
		auth.add(new SimpleGrantedAuthority(MyAuthority.USER.name()));
		if(userId.equals("admin"))
			auth.add(new SimpleGrantedAuthority(MyAuthority.ADMIN.name()));
		return auth;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userId;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
