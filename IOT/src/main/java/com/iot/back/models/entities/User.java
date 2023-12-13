package com.iot.back.models.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.iot.back.models.embedding.Login;
import com.iot.back.models.embedding.ShoppingCart;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Automatically generated user_id", example = "ae45b37e-9fa1-4f9e-8bc7-a93d9ce2e7e2")
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String user_id;

	@Schema(implementation = Login.class)
	@Embedded
	private Login login;
	@Schema(description = "user name", example = "Maria Tereza")

	private String nome;
	@Schema(description = "user email", example = "maria.tereza@gmail.com")

    private String email;
	@Schema(description = "user photo on string base64")
    @Column(columnDefinition = "TEXT")
    private String image;
    @Schema(implementation = ShoppingCart.class)
	@Embedded
    @ElementCollection

    private List<ShoppingCart> shoppingCart = new ArrayList<>();


    // Construtor vazio
    public User() {
    }

    // Construtor com todos os campos
    public User(String nome, String email, Login login) {
        this.nome = nome;
        this.email = email;
        this.login= login;
        this.image = "";
    }

    
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// Getters e Setters para todos os campos
    public String getNome() {
        return nome;
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
    
	public List<ShoppingCart> getShoppingCart() {
		if (shoppingCart == null) {
            shoppingCart = new ArrayList<>();
        }
        return shoppingCart;	}

	public void setShoppingCart(List<ShoppingCart> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	// UserDetails
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return getLogin().getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getLogin().getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
	    return true; // Modifique para refletir a lógica apropriada de expiração da conta
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true; // Modifique para refletir a lógica apropriada de bloqueio da conta
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true; // Modifique para refletir a lógica apropriada de expiração das credenciais
	}

	@Override
	public boolean isEnabled() {
	    return true; // Modifique para refletir a lógica apropriada de ativação da conta
	}

}

