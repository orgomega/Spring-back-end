package com.springboot.second.app.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data
@Entity
@Table(name="Revenues")
@Proxy(lazy = false)
public class Revenue implements Serializable   {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8669006110756595754L;
	public Revenue() {
	  
	}
	public Revenue(String nom, String description, String dat_ver, String type, long montant, User user,Compte compte,String category) {
		super();
		this.nom = nom;
		this.description = description;
		this.dat_ver = dat_ver;
		this.type = type;
		this.montant = montant;
		this.user = user;
		this.compte=compte;
		this.category = category;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDat_ver() {
		return dat_ver;
	}
	public void setDat_ver(String dat_ver) {
		this.dat_ver = dat_ver;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getMontant() {
		return montant;
	}
	public void setMontant(long montant) {
		this.montant = montant;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	 public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Id	
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long id;
	 @Column (name = "name")
	 private String nom ;
	 @Column (name = "desc")
	 private String description ;
	 @Column (name = "date")
	 private String dat_ver ;
	 @Column (name = "type")
	 private String type ;
	 @Column (name = "amount")
	 private long montant ;
	 @Column (name = "category")
	 private String category ;
	 @ManyToOne (fetch = FetchType.LAZY, optional = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name ="user_id",nullable = false)
	 private User user;
	 @ManyToOne (fetch = FetchType.LAZY, optional = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name ="compte_id",nullable = false)
	 private Compte compte;
	@Override
	public String toString() {
		return "Revenue [id=" + id + ", nom=" + nom + ", description=" + description + ", dat_ver=" + dat_ver
				+ ", type=" + type + ", montant=" + montant + ", user=" + user + "]";
	}
}
