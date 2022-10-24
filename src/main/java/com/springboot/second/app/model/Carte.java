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
@Table(name="Cartes")
@Proxy(lazy = false)
public class Carte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8220675704097645066L;
	
	public Carte() {
	
	}
	 public Carte(String name, String limit, String interest_rate, Compte compte,User user) {
		super();
		this.name = name;
		this.limit = limit;
		this.interest_rate = interest_rate;
		this.compte = compte;
		this.user=user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(String interest_rate) {
		this.interest_rate = interest_rate;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	 @Override
	public String toString() {
		return "Carte [id=" + id + ", name=" + name + ", limit=" + limit + ", interest_rate=" + interest_rate
				+ ", compte=" + compte + ", user=" + user + "]";
	}

	@Id	
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long id;
	 @Column (name = "name")
	 private String name;
	 @Column (name = "limit")
	 private String limit;
	 @Column (name ="interest_rate")
	 private String interest_rate;
	 @ManyToOne (fetch = FetchType.LAZY, optional = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name ="compte_id",nullable = false)
	 private Compte compte;
	 @ManyToOne (fetch = FetchType.LAZY, optional = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name ="user_id",nullable = false)
	 private User user;

}
