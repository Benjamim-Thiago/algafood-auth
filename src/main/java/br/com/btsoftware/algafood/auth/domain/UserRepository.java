package br.com.btsoftware.algafood.auth.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials, Long>{
	Optional<UserCredentials> findByEmail(String email);
	
}
