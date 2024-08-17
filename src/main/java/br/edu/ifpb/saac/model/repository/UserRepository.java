package br.edu.ifpb.saac.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.saac.model.entity.User;

public interface UserRepository extends JpaRepository <User, Integer>{
	
	public Optional<User> findByName(String name);
	public boolean existsByName(String name);
	public Optional<User> findByRegistration(Long registration);
	public boolean existsByRegistration(Long registration);
	
}
