package com.quinto.entidades;

import java.time.LocalDate;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Alumno {

	//atributos
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String nombre;
	private int edad;
	private String mail;
	private String clave;
	private boolean alta;
	@Temporal(TemporalType.DATE)
	private LocalDate fechaNacimiento;
	private String historia;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "alumnos")
	//@JoinColumn(name = "uId", foreignKey = @ForeignKey(name = "uId", value =ConstraintMode.CONSTRAINT))
	private List<Curso> cursos = new ArrayList<>();
	
	
	//constructores
	//por defecto
	public Alumno() {
	}

	//con todos los atributos
	public Alumno(String id, String nombre, int edad, String mail, String clave, boolean alta,
			LocalDate fechaNacimiento, String historia, List<Curso> cursos) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.mail = mail;
		this.clave = clave;
		this.alta = alta;
		this.fechaNacimiento = fechaNacimiento;
		this.historia = historia;
		this.cursos = cursos;
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


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getHistoria() {
		return historia;
	}


	public void setHistoria(String historia) {
		this.historia = historia;
	}


	public List<Curso> getCursos() {
		return cursos;
	}


	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
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
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
}
