package br.com.btsoftware.algafood.auth.core;


import java.util.Collections;

import org.springframework.security.core.userdetails.User;

import br.com.btsoftware.algafood.auth.domain.UserCredentials;
import lombok.Getter;

@Getter
public class AuthUser extends User{

	private static final long serialVersionUID = 1L;
	private String fullName;
	
	public AuthUser(UserCredentials user) {
		super(user.getEmail(), user.getPassword(), Collections.emptyList());
		
		this.fullName = user.getName();
	}

}
