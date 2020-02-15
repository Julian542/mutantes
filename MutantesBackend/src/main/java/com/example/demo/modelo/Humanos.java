package com.example.demo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Humanos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "humano_id")
	private int id;
	
	private boolean eshumano;
	
	public Humanos() {}

	public Humanos(int id, boolean eshumano) {
		super();
		this.id = id;
		this.eshumano = eshumano;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getEshumano() {
		return eshumano;
	}

	public void setEshumano(boolean eshumano) {
		this.eshumano = eshumano;
	}

	
	
	
}
