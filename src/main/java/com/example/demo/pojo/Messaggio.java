package com.example.demo.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Messaggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Il campo email non può essere vuoto")
	@Size(min = 3, message = "L'email deve essere di almeno 3 caratteri")
	private String email;
	
	@NotBlank(message = "Il messaggio non può essere vuoto")
	@Size(min = 3, message = "Il nome deve essere di almeno 3 caratteri")
	private String messaggio;
	
	public Messaggio () {}
	public Messaggio (String email, String messaggio) {
		
		setEmail(email);
		setMessaggio(messaggio);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	
}
