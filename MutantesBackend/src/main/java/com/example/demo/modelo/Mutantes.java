package com.example.demo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mutantes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mutante_id")
	private int id;
	
	private boolean esmutante;
	public Mutantes() {}

	public Mutantes(int id, boolean esmutante) {
		super();
		this.id = id;
		this.esmutante = esmutante;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getEsmutante() {
		return esmutante;
	}

	public void setEsmutante(boolean esmutante) {
		this.esmutante = esmutante;
	}

	 
	
}
