package com.example.demo.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Il titolo non può essere vuoto")
	@Size(min = 3, message = "Il titolo deve essere di almeno 3 caratteri")
	private String titolo;
	
	@NotBlank(message = "La descrizione non può essere vuota")
	@Size(min = 5, message = "La descrizione deve essere di almeno 5 caratteri")
	private String descrizione;
	
	@NotBlank(message = "Inserisci almeno un'immagine")
	private String url;
	
	private Boolean visibile = true;
	
	@ManyToMany
	private List<Categoria> categorie;
	
	public Foto() { };
	public Foto (String titolo, String descrizione, String url, Boolean visibile, Categoria... categorie) {
		
		setTitolo(titolo);
		setDescrizione(descrizione);
		setUrl(url);
		if(visibile != null) {
			setVisibile(visibile);
		}
		setCategorie(categorie);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getVisibile() {
		return visibile;
	}
	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}
	public List<Categoria> getCategorie() {
		return categorie;
	}
	@JsonSetter
	public void setCategorie(List<Categoria> categorie) {
		
		this.categorie = categorie;
	}
	public void setCategorie(Categoria[] categorie) {
		setCategorie(Arrays.asList(categorie)); 
	}
	public void removeCategorie(Categoria categorie) {
		
		getCategorie().remove(categorie);
	}
	
	
	

}
