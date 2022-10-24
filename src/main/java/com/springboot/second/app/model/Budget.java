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
@Table(name="Budgets")
@Proxy(lazy = false)
public class Budget implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5061349528127821847L;

	public Budget() {
	}
	 public Budget(String name, String value, String duration, Compte compte, String category,User user) {
		super();
		this.name = name;
		this.value = value;
		this.duration = duration;
		this.compte = compte;
		this.category = category;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	
	
	 public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	@Override
	public String toString() {
		return "Budget [id=" + id + ", name=" + name + ", value=" + value + ", duration=" + duration + ", compte="
				+ compte + ", category=" + category + ", user=" + user + "]";
	}
	@Id	
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long id;
	 @Column (name = "name")
	 private String name;
	 @Column (name = "value")
	 private String value;
	 @Column (name ="duration")
	 private String duration;
	 @ManyToOne (fetch = FetchType.LAZY, optional = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name ="compte_id",nullable = false)
	 private Compte compte;
	 @Column (name ="category")
	 private String category;
	 @ManyToOne (fetch = FetchType.LAZY, optional = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name ="user_id",nullable = false)
	 private User user;

}
