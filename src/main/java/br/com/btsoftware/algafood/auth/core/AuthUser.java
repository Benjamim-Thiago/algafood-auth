package br.com.btsoftware.algafood.auth.core;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.btsoftware.algafood.auth.domain.UserCredentials;
import lombok.Getter;

@Getter
public class AuthUser extends User{

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String fullName;
	
	public AuthUser(UserCredentials user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		
		this.userId = user.getId();		
		this.fullName = user.getName();
	}

}
