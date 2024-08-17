package br.edu.ifpb.saac.business.service;

import java.util.List;
import java.util.Optional;

import br.edu.ifpb.saac.presentation.exception.MissingFieldException;
import br.edu.ifpb.saac.model.entity.User;
import br.edu.ifpb.saac.model.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.saac.presentation.exception.ObjectAlreadyExistsException;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}
	
	public boolean existsByRegistration(Long registration) {
		return userRepository.existsByRegistration(registration);
	}
	
	public User findById(Integer id) throws Exception {
		if (!existsById(id)) {
			throw new ObjectNotFoundException("usuário", "id");
		}
		return userRepository.getById(id);
	}
	
	public Optional<User> findByName(String name) throws Exception {
		if (name == null || name.isBlank()) {
			throw new MissingFieldException("nome");
		}
		
		if (!userRepository.existsByName(name)) {
			throw new ObjectNotFoundException("usuário", "nome");
		}
		return userRepository.findByName(name);
	}
	
	public Optional<User> findByRegistration(Long registration) throws Exception {
		if (registration == null) {
			throw new MissingFieldException("matrícula");
		}
		
		if (!existsByRegistration(registration)) {
			throw new ObjectNotFoundException("usuário", "matrícula");
		}
		
		return userRepository.findByRegistration(registration);
	}
	
	public User save(User user) throws Exception {
		if (user.getName() == null || user.getName().isBlank()) {
			throw new MissingFieldException("nome", "save");
		}
		
		if (existsByRegistration(user.getRegistration())) {
			throw new ObjectAlreadyExistsException("Já existe um usuário com matrícula " + user.getRegistration());
		}
		
		return userRepository.save(user);
	}
	
	public User update(User user) throws Exception {
		if (user.getName() == null || user.getName().isBlank()) {
			throw new MissingFieldException("nome", "update");
		}
		
		if (user.getId() == null) {
			throw new MissingFieldException("id", "update");
		} else if (!existsById(user.getId())) {
			throw new ObjectNotFoundException("usuário", "id");
		}
		
		if (existsByRegistration(user.getRegistration())) {
			User userSaved = findByRegistration(user.getRegistration()).get();
			
			if (userSaved.getId() != (user.getId().intValue())) {
				
				throw new ObjectAlreadyExistsException("Já existe um usuário com matrícula " + user.getRegistration());
			}
		}
		
		return userRepository.save(user);
	}
	
	public void delete(User user) throws Exception {
		if (user.getId() == null) {
			throw new MissingFieldException("id", "delete");
		} else if (!existsById(user.getId())) {
			throw new ObjectNotFoundException("usuário", "id");
		}
		
		userRepository.delete(user);
	}
	
	public void deleteById(Integer id) throws Exception {
		if (id == null) {
			throw new MissingFieldException("id", "delete");
		} else if (!existsById(id)) {
			throw new ObjectNotFoundException("usuário", "id");
		}
		
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = findByRegistration(Long.parseLong(username)).get();
			return user;
		} catch (Exception e) {
			throw new UsernameNotFoundException("Não pode ser encontrado nenhum usuário com matrícula :" + username);
		}
	}

}