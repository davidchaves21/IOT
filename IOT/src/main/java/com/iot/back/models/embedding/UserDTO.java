package com.iot.back.models.embedding;

import java.io.Serializable;

import com.iot.back.models.entities.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
    private String email;
    private String image;


    // Construtor vazio
    public UserDTO() {
    }

    // Construtor com todos os campos
    public UserDTO(User user) {
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.image = user.getImage();
    }

  
	
    // Getters e Setters para todos os campos
    public String getNome() {
        return nome;
    }
    
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
