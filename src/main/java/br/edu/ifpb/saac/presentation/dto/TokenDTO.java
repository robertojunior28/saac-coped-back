package br.edu.ifpb.saac.presentation.dto;

public class TokenDTO {

	private String token;
	private UserDTO user;

	public TokenDTO() {
		
	}
	
	public TokenDTO(String token, UserDTO userDto) {
		this.token = token;
		this.user = userDto;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}