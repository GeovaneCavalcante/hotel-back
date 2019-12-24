package com.hoteis.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.hoteis.apirest.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findById(long id);

	Optional<User> findByEmail(String email);
	Optional<User> findByEmail(User user);

	Boolean existsByEmail(String email);
}
