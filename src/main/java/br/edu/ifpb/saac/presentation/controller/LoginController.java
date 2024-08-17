package br.edu.ifpb.saac.presentation.controller;

import br.edu.ifpb.saac.business.service.TokenService;
import br.edu.ifpb.saac.business.service.UserConverterService;
import br.edu.ifpb.saac.business.service.UserService;
import br.edu.ifpb.saac.model.entity.User;
import br.edu.ifpb.saac.presentation.dto.LoginDTO;
import br.edu.ifpb.saac.presentation.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.saac.business.service.LoginService;
import br.edu.ifpb.saac.presentation.dto.UserDTO;

@RestController
@RequestMapping("/api")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LoginController {
	
	@Autowired
	private LoginService service;
	@Autowired
	private UserConverterService userConverter;
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO dto) {
		try {
			String token = service.login(dto.getUsername(), dto.getPassword());
			User entity = userService.findByRegistration(Long.valueOf(dto.getUsername())).get();
			UserDTO userDTO = userConverter.userToDto(entity);
			TokenDTO tokenDTO = new TokenDTO(token, userDTO);
			
			return new ResponseEntity(tokenDTO, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/isValidToken")
	public ResponseEntity<Comparable> isValidToken(@RequestBody TokenDTO tokenDto) {
		try {
			boolean isValidToken = tokenService.isValid(tokenDto.getToken());
			
			return new ResponseEntity<Comparable>(isValidToken, HttpStatus.OK);
		} catch(Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
