package com.example.demo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adn_id")
	private int id;
	
	private String dna1;
	private String dna2;
	private String dna3;
	private String dna4;
	private String dna5;
	private String dna6;
	
	public Adn() {
		
	}
	public Adn(int id, String dna1, String dna2, String dna3, String dna4, String dna5, String dna6) {
		super();
		this.id = id;
		this.dna1 = dna1;
		this.dna2 = dna2;
		this.dna3 = dna3;
		this.dna4 = dna4;
		this.dna5 = dna5;
		this.dna6 = dna6;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDna1() {
		return dna1;
	}
	public void setDna1(String dna1) {
		this.dna1 = dna1;
	}
	public String getDna2() {
		return dna2;
	}
	public void setDna2(String dna2) {
		this.dna2 = dna2;
	}
	public String getDna3() {
		return dna3;
	}
	public void setDna3(String dna3) {
		this.dna3 = dna3;
	}
	public String getDna4() {
		return dna4;
	}
	public void setDna4(String dna4) {
		this.dna4 = dna4;
	}
	public String getDna5() {
		return dna5;
	}
	public void setDna5(String dna5) {
		this.dna5 = dna5;
	}
	public String getDna6() {
		return dna6;
	}
	public void setDna6(String dna6) {
		this.dna6 = dna6;
	}
	
	
}
