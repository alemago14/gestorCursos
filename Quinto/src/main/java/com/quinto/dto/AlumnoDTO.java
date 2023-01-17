package com.quinto.dto;

import java.time.LocalDate;
import java.util.ArrayList;

import com.quinto.entidades.Curso;

public class AlumnoDTO {

	//atributos
	private String id;
	private String nombre;
	private int edad;
	private LocalDate fechaNacimiento;
	private String historia;
	private ArrayList<Curso> cursos;
	private boolean alta;
	
	//constructor por defecto
	public AlumnoDTO() {
	}

	//constructor con todos los campos
	public AlumnoDTO(String id, String nombre, int edad, LocalDate fechaNacimiento, String historia,
			ArrayList<Curso> cursos, boolean alta) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
		this.historia = historia;
		this.cursos = cursos;
		this.alta = alta;
	}

	///metodos getters y setters
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}


	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}


	/**
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	/**
	 * @return the historia
	 */
	public String getHistoria() {
		return historia;
	}


	/**
	 * @param historia the historia to set
	 */
	public void setHistoria(String historia) {
		this.historia = historia;
	}


	/**
	 * @return the cursos
	 */
	public ArrayList<Curso> getCursos() {
		return cursos;
	}


	/**
	 * @param cursos the cursos to set
	 */
	public void setCursos(ArrayList<Curso> cursos) {
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
	
	
}
