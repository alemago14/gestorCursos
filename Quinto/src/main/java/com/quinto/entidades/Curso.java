package com.quinto.entidades;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.quinto.enums.Turno;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Curso {

	//atributos
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String nombre;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "profesor_id", foreignKey = @ForeignKey(name = "profesor_id"))
	private Profesor profesor;
	private Turno turno;
	@Temporal(TemporalType.TIME)
	private LocalTime horarioInicio;
	@Temporal(TemporalType.TIME)
	private LocalTime horarioFin;
	private boolean alta;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "cursoAlumno",joinColumns = {@JoinColumn(name = "curso_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "alumno_id", referencedColumnName = "id")})
	private List<Alumno> alumnos = new ArrayList<>();
	
	//constructores
	//con todos los campos
	public Curso(String id, String nombre, Profesor profesor, Turno turno, LocalTime horarioInicio,
			LocalTime horarioFin, boolean alta, List<Alumno> alumnos) {
		this.id = id;
		this.nombre = nombre;
		this.profesor = profesor;
		this.turno = turno;
		this.horarioInicio = horarioInicio;
		this.horarioFin = horarioFin;
		this.alta = alta;
		this.alumnos = alumnos;
	}

	//constructor por defecto
	public Curso() {
	}

	//metodos setters y getters
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


	public Profesor getProfesor() {
		return profesor;
	}


	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}


	public Turno getTurno() {
		return turno;
	}


	public void setTurno(Turno turno) {
		this.turno = turno;
	}


	public List<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	/**
	 * @return the horarioInicio
	 */
	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	/**
	 * @param horarioInicio the horarioInicio to set
	 */
	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	/**
	 * @return the horarioFin
	 */
	public LocalTime getHorarioFin() {
		return horarioFin;
	}

	/**
	 * @param horarioFin the horarioFin to set
	 */
	public void setHorarioFin(LocalTime horarioFin) {
		this.horarioFin = horarioFin;
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
