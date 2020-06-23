package edu.miu.cs.cs544.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.miu.cs.cs544.domain.User;

public class SecurityUserDetails implements UserDetails {

	private static final long serialVersionUID = 5155720064139820502L;

	private String password;

	private final String username;
	private final Set<? extends GrantedAuthority> authorities;
	private final boolean accountNonExpired = true;
	private final boolean accountNonLocked = true;
	private final boolean credentialsNonExpired = true;
	private final boolean enabled = true;

	public SecurityUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		
		if(null == user.getRoles() || user.getRoles().isEmpty()) {
			this.authorities = new HashSet<>();  
		}
		else {
			this.authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}