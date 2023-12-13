package com.iot.back.models.entities.user;

public class UserRegisterRequest {
	
	private String nome;
    private String email;
    private String username;
    private String password;
    

    // Construtor vazio
    public UserRegisterRequest() {
    }

    // Construtor com todos os campos
    public UserRegisterRequest(String nome, String email, String username, String password) {
        this.nome = nome;
        this.email = email;
        this.username= username;
        this.password = password;
        
    }
    
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRegisterRequest [nome=" + nome + ", email=" + email + ", username=" + username + ", password="
				+ password + "]";
	}

	
	
	


}
