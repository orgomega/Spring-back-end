package com.springboot.second.app.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Proxy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
@Entity
@Table(name="Users")
@Proxy(lazy = false)
public class User implements Serializable  {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6875315734182911681L;

	public User() {
		
	}
	public User(String nom, String prenom, String email, String cin, String username, String password,String country, String city,String adress,String codepostal) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.cin = cin;
		this.username = username;
		this.password = password;
		this.adress=adress;
		this.city=city;
		this.country = country ;
		this.codepostal=codepostal;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Revenue> getRevenues() {
		return revenues;
	 }
	 public void setRevenues(List<Revenue> revenues) {
		this.revenues = revenues;
	 }
	 public List<Depense> getDepenses() {
		return depenses;
	 }
	 public void setDepenses(List<Depense> depenses) {
		this.depenses = depenses;
	 }
	 
	 public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCodepostal() {
		return codepostal;
	}
	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public List<Carte> getCartes() {
		return cartes;
	}
	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}

	@Id	
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long id;
	 @Column (name = "firstname")
	 private String nom ;
	 @Column (name = "lastname")
	 private String prenom ;
	 @Column (name = "email")
	 private String email ;
	 @Column (name = "country")
	 private String country;
	 @Column (name = "city")
	 private String city;
	 @Column (name = "adress")
	 private String adress;
	 @Column (name = "codepostal")
	 private String codepostal;
	 @Column (name = "cin")
	 @JsonIgnore
	 private String cin ;
	 @Column (name = "username")
	 private String username ;
	 @Column (name = "cpf")
	 @JsonIgnore
	 private String password ; 
	 @JsonIgnore
	 @OneToMany(targetEntity = Revenue.class, mappedBy = "user", fetch = FetchType.LAZY)
	 @OnDelete(action = OnDeleteAction.NO_ACTION)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private List<Revenue> revenues ;
	 @JsonIgnore
	 @OneToMany(targetEntity = Depense.class, mappedBy = "user", fetch = FetchType.LAZY) 
	 @OnDelete(action = OnDeleteAction.NO_ACTION)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private List<Depense> depenses ;
	 @JsonIgnore
	 @OneToMany(targetEntity = Carte.class, mappedBy = "user", fetch = FetchType.LAZY) 
	 @OnDelete(action = OnDeleteAction.NO_ACTION)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private List<Carte> cartes;

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", country=" + country
				+ ", city=" + city + ", adress=" + adress + ", codepostal=" + codepostal + ", cin=" + cin
				+ ", username=" + username + ", password=" + password + "]";
	}
	 
	
}
