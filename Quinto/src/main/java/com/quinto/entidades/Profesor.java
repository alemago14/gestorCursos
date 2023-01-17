package com.quinto.entidades;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Profesor {

	//Atributos
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String nombre;
	private String apellido;
	private String mail;
	private boolean alta;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "profesor_id", foreignKey = @ForeignKey(name = "profesor_id", value = ConstraintMode.CONSTRAINT))
	private List<Curso> cursos = new ArrayList<>();
	
	//constructores
	//con todos los campos

	public Profesor(String id, String nombre, String apellido, String mail, boolean alta, List<Curso> cursos) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.alta = alta;
		this.cursos = cursos;
	}


	//por defecto
	public Profesor() {
	}

	//getters y setters
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public List<Curso> getCursos() {
		return cursos;
	}


	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}


	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}


	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}


	/**
	 * @return the alta
	 */
	public boolean isAlta() {
		return alta;
	}


	/**
	 * @param alta the alta to set
	 */
	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
}
