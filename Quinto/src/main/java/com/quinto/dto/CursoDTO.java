package com.quinto.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.quinto.entidades.Alumno;
import com.quinto.entidades.Profesor;
import com.quinto.enums.Turno;

public class CursoDTO {

	//atributos
	private String id;
	private String nombre;
	private Profesor profesor;
	private Turno turno;
	private LocalTime horario;
	private List<Alumno> alumnos;
	
	//constructor por defecto
	public CursoDTO() {
	}

	//constructor con todos los campos
	public CursoDTO(String id, String nombre, Profesor profesor, Turno turno, LocalTime horario, List<Alumno> alumnos) {
		this.id = id;
		this.nombre = nombre;
		this.profesor = profesor;
		this.turno = turno;
		this.horario = horario;
		this.alumnos = alumnos;
	}

	//metodos getter y setter
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
	 * @return the profesor
	 */
	public Profesor getProfesor() {
		return profesor;
	}


	/**
	 * @param profesor the profesor to set
	 */
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}


	/**
	 * @return the turno
	 */
	public Turno getTurno() {
		return turno;
	}


	/**
	 * @param turno the turno to set
	 */
	public void setTurno(Turno turno) {
		this.turno = turno;
	}


	/**
	 * @return the alumnos
	 */
	public List<Alumno> getAlumnos() {
		return alumnos;
	}


	/**
	 * @param alumnos the alumnos to set
	 */
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	/**
	 * @return the horario
	 */
	public LocalTime getHorario() {
		return horario;
	}

	/**
	 * @param horario the horario to set
	 */
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
	
}
