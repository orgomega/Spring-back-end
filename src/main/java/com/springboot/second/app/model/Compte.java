package com.springboot.second.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name="Comptes")
@Proxy(lazy = false)
@Builder
public class Compte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5540382368808844418L;
     
	public Compte() {
		
	}
	
	 public Compte(String name, String currency, String balance,User user) {
		super();
		this.name = name;
		this.currency = currency;
		this.balance = balance;
		this.user =user;
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}

	public List<Dette> getDettes() {
		return dettes;
	}

	public void setDettes(List<Dette> dettes) {
		this.dettes = dettes;
	}

	public List<Budget> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	



	@Id	
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long id;
	 @Column (name = "name")
	 private String name;
	 @Column (name = "currency")
	 private String currency;
	 @Column (name = "balance")
	 private String balance;
	 @JsonIgnore
	 @OneToMany(targetEntity = Carte.class, mappedBy = "compte", fetch = FetchType.LAZY)
	 @OnDelete(action = OnDeleteAction.NO_ACTION)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private List<Carte> cartes ;
	 @JsonIgnore
	 @OneToMany(targetEntity = Carte.class, mappedBy = "compte", fetch = FetchType.LAZY)
	 @OnDelete(action = OnDeleteAction.NO_ACTION)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private List<Dette> dettes ;
	 @JsonIgnore
	 @OneToMany(targetEntity = Carte.class, mappedBy = "compte", fetch = FetchType.LAZY)
	 @OnDelete(action = OnDeleteAction.NO_ACTION)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private List<Budget> budgets ;
	 @JsonIgnore
	 @OneToMany(targetEntity = Carte.class, mappedBy = "compte", fetch = FetchType.LAZY)
	 @OnDelete(action = OnDeleteAction.NO_ACTION)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private List<Depense> depenses ;
	 @JsonIgnore
	 @OneToMany(targetEntity = Carte.class, mappedBy = "compte", fetch = FetchType.LAZY)
	 @OnDelete(action = OnDeleteAction.NO_ACTION)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 private List<Revenue> revenues ;
	 @ManyToOne (fetch = FetchType.LAZY, optional = false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @JoinColumn(name ="user_id",nullable = false)
	 private User user;
}

