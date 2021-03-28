package br.com.btsoftware.algafood.auth.core;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.btsoftware.algafood.auth.domain.UserCredentials;
import br.com.btsoftware.algafood.auth.domain.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCredentials user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));
		
		return new AuthUser(user, getAuthorities(user));
	}
	
	private Collection<GrantedAuthority> getAuthorities(UserCredentials user) {
		return user.getGroups().stream()
				.flatMap(Group -> Group.getPermissions().stream())
				.map(Permission -> new SimpleGrantedAuthority(Permission.getName().toUpperCase()))
				.collect(Collectors.toSet());
	}

}
