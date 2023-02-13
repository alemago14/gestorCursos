package com.quinto.servicios;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinto.dto.CursoDTO;
import com.quinto.entidades.Curso;
import com.quinto.entidades.Profesor;
import com.quinto.enums.Turno;
import com.quinto.excepciones.WebException;
import com.quinto.repositorios.CursoRepositorio;
import com.quinto.repositorios.ProfesorRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CursoServicio {

	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Autowired
	private ProfesorRepositorio profesorRepositorio;
	
	//metodo para crear un curso nuevo
	@Transactional
	public void crearCurso(String nombre, String turno, LocalTime horarioI, LocalTime horarioF, String nombreProfesor) throws WebException {
		Curso curso = new Curso();
		
		//validamos los datos
		validarString(nombre);
		
		//comprobamos que no exista en la base de datos
		Optional<Curso> resp = cursoRepositorio.buscarPorNombre(nombre);
		if(resp.isPresent()) {
			throw new WebException("EL curso ya existe en la base de datos");
		}
		
		curso.setNombre(nombre);
		curso.setHorarioInicio(horarioI);
		curso.setHorarioFin(horarioF);
		if(turno.equals("1 - Mañana")) {
			curso.setTurno(Turno.MAÑANA);
		}else {
			if(turno.equals("2 - Tarde")) {
				curso.setTurno(Turno.TARDE);
			}else {
				if(turno.equals("3 - Noche")) {
					curso.setTurno(Turno.NOCHE);}
			}
		}
		
		if(!nombreProfesor.equals("Sin profesor")) {
			String[] textoSeparado = nombreProfesor.split("\s");
			System.out.println(textoSeparado[0]+textoSeparado[1]);
			Profesor profesor = profesorRepositorio.buscarPorNombreApellido(textoSeparado[0], textoSeparado[1]).get();		
			curso.setProfesor(profesor);
		}
		
		cursoRepositorio.save(curso);
	}
	
	//metodo para modifca un curso
	@Transactional
	public void modificarCurso(String id, String nombre, String turno, LocalTime horarioI, LocalTime horarioF, String nombreProfesor) throws WebException {
		Curso curso = new Curso();
		
		//validamos los datos
		validarString(id);
		validarString(nombre);
		validarString(turno);
		validarString(nombreProfesor);
		
		//comprobamos si esta en la base de datos
		Optional<Curso> resp = cursoRepositorio.findById(id);
		
		if(resp.isPresent()) {
			curso = resp.get();
			
			curso.setNombre(nombre);
			curso.setHorarioInicio(horarioI);
			curso.setHorarioFin(horarioF);
			if(turno.equals("1 - Mañana")) {
				curso.setTurno(Turno.MAÑANA);
			}else {
				if(turno.equals("2 - Tarde")) {
					curso.setTurno(Turno.TARDE);
				}else {
					if(turno.equals("3 - Noche")) {
						curso.setTurno(Turno.NOCHE);}
				}
			}
			if(!nombreProfesor.equals("Sin profesor")) {
				String[] textoSeparado = nombreProfesor.split("\s");
				System.out.println(textoSeparado[0]+textoSeparado[1]);
				Profesor profesor = profesorRepositorio.buscarPorNombreApellido(textoSeparado[0], textoSeparado[1]).get();		
				curso.setProfesor(profesor);
			}

			cursoRepositorio.save(curso);
		}else {
			throw new WebException("No existe el curso en la base de datos");
		}
	}
	
	//metodo para elminar un curso de la base de datos
	@Transactional
	public void eliminarCurso(String id) throws WebException {
		Curso curso = new Curso();
		
		//validamos los datos
		validarString(id);
		
		//comprobamos si existe en la base de datos
		Optional<Curso> resp = cursoRepositorio.findById(id);
		
		if(resp.isPresent()) {
			curso = resp.get();
			
			cursoRepositorio.delete(curso);
		}else {
			throw new WebException("El curso no se encuentra en la base de datos");
		}
	}
	
	//metodo para modifcar el profesor de un curso
	@Transactional
	public void cambiarProfesor(String idCurso, String idProfesor) throws WebException {
		//validamos los datos
		if(idCurso.isBlank() || idProfesor.isBlank()) {
			throw new WebException("Los campos no tienen que estar vacios");
		}
		
		Curso curso = new Curso();
		Profesor profesor = new Profesor();
		
		//comprobamos que el curso y el profesor existan en la base de datos
		Optional<Curso> respCurso = cursoRepositorio.findById(idCurso);
		Optional<Profesor> respProfesor = profesorRepositorio.findById(idProfesor);
		
		if(respCurso.isPresent() && respProfesor.isPresent()) {
			curso = respCurso.get();
			profesor = respProfesor.get();
			
			curso.setProfesor(profesor);
			
			cursoRepositorio.save(curso);
		}else {
			throw new WebException("No existe en la base de datos");
		}
	}
	
	//metodo para buscar un curso por id
	public Curso buscarPorId(String id) throws WebException {
		Optional<Curso> resp = cursoRepositorio.findById(id);
		
		if(resp.isPresent()) {
			Curso curso = resp.get();
			return curso;
		}else {
			throw new WebException("No se encuentra en la base de datos");
		}
	}
	
	//metodo para listar todos los cursos
	public List<Curso> TodosLosCursos(){
		return cursoRepositorio.findAll();
	}
	
	
	//metodo para listar los cursos sin profesor
	public List<Curso> cursoSinProfesor() throws WebException{
		Optional<List<Curso>> resp = cursoRepositorio.cursosnProfesor();
		
		if(resp.isPresent()) {
			return resp.get();
		}else {
			throw new WebException("No hay cursos disponibles");}
			
	}
	
	//metodo para listar los cursos sin profesor
		public List<Curso> cursoAlumno(String id) throws WebException{
			Optional<List<Curso>> resp = cursoRepositorio.cursoDistinto(id);
			
			if(resp.isPresent()) {
				return resp.get();
			}else {
				throw new WebException("No hay cursos disponibles");}
				
		}
	
	//metodo paa validar los datos del curso
	public void validarString(String nombre) throws WebException {
		if(nombre.isBlank()) {
			throw new WebException("Debe ingresar un nombre al curso");
		}
	}
}
