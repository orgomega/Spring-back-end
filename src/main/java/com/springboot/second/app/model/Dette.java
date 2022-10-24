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
@Table(name="Dette")
@Proxy(lazy = false)
public class Dette implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8273980920550031289L;
	
	public Dette() {

	}	
	 public Dette(String name, String value, String date, String pay_date, String from, Compte compte,User user) {
		super();
		this.name = name;
		this.value = value;
		this.date = date;
		this.pay_date = pay_date;
		this.from = from;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	 @Id	
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long id;
	 @Column (name = "name")
	 private String name;
	 @Column (name = "value")
	 private String value;
	 @Column (name ="date")
	 private String date;
	 @Column (name ="pay_date")
	 private String pay_date;
	 @Column (name ="from")
	 private String from;
	 @ManyToOne (fetch = FetchType.LAZY, optional = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name ="compte_id",nullable = false)
	 private Compte compte;
	 @ManyToOne (fetch = FetchType.LAZY, optional = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name ="user_id",nullable = false)
	 private User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
