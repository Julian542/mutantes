package com.example.demo.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Ente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ente_id")
	private int id;
	
	private String nombre;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ente_fk_adn")
	private Adn adn;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ente_fk_humanos")
	private Humanos humano;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ente_fk_mutantes")
	private Mutantes mutante;
	
	private String clasificacion;
	public Ente() {
		
	}
	public Ente(int id, String nombre, Adn adn,Humanos humano,Mutantes mutante, String clasificacion ) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.adn = adn;
		this.humano = humano;
		this.mutante = mutante;
		this.clasificacion = clasificacion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Adn getAdn() {
		return adn;
	}
	public void setAdn(Adn adn) {
		this.adn = adn;
	}
	public Humanos getHumano() {
		return humano;
	}
	public void setHumano(Humanos humano) {
		this.humano = humano;
	}
	public Mutantes getMutante() {
		return mutante;
	}
	public void setMutante(Mutantes mutante) {
		this.mutante = mutante;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
		
}
