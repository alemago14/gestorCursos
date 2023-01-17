package com.quinto.dto;

import java.util.ArrayList;

import com.quinto.entidades.Curso;

public class ProfesorDTO {

	//atributos
	private String id;
	private String nombre;
	private String apellido;
	private ArrayList<Curso> cursos;
	
	//constructor con parametros
	public ProfesorDTO(String id, String nombre, String apellido, ArrayList<Curso> cursos) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cursos = cursos;
	}

	//constructor por defecto
	public ProfesorDTO() {
	}

	//metodos getters y setters
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}


	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	
	
	
	
}
