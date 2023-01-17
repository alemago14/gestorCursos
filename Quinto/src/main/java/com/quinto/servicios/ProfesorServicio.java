package com.quinto.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinto.dto.ProfesorDTO;
import com.quinto.entidades.Curso;
import com.quinto.entidades.Profesor;
import com.quinto.excepciones.WebException;
import com.quinto.repositorios.ProfesorRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ProfesorServicio {

	@Autowired
	private CursoServicio cursoServicio;
	
	@Autowired
	private ProfesorRepositorio profesorRepositorio;
	
	//metodo para regstrar un nuevo profesor
	@Transactional
	public void crearProfesor(String nombre, String apellido, String mail) throws WebException {
		Profesor profesor = new Profesor();
		
		//validamos si no esta vacio
		validarString(nombre);
		validarString(apellido);
		validarString(mail);
		
		//comprobamos que no exsta el registro en la bd
		Optional<Profesor> resp = profesorRepositorio.buscarPorNombreApellido(nombre, apellido);
		if(resp.isPresent()) {
			throw new WebException("El profesor ya se encuentra en la base de datos");
		}else {
			profesor.setNombre(nombre);
			profesor.setApellido(apellido);
			profesor.setMail(mail);
			profesor.setAlta(true);
			
			profesorRepositorio.save(profesor);
		}
		
	}
	
	//modificar un profesor
	@Transactional
	public void actualizarProfesor(String id, String nombre, String apellido, String mail) throws WebException {
		//validamos si no esta vacio
				validarString(nombre);
				validarString(apellido);
				validarString(mail);
		
		Optional<Profesor> resp = profesorRepositorio.findById(id);
		
		if(resp.isPresent()) {
			Profesor profesor = resp.get();
			
			profesor.setNombre(nombre);
			profesor.setApellido(apellido);
			profesor.setMail(mail);
			
			profesorRepositorio.save(profesor);
		}else {
			throw new WebException("El rofesor no s encuentra en la base de datos.");
		}
	}
	
	//metodo para eliminar un profesor
	@Transactional
	public void eliminarProfesor(String id) throws WebException {
		//validamos el dto
		validarString(id);
		
		//comprobamos s esta en la base de datos
		Optional<Profesor> resp = profesorRepositorio.findById(id);
		
		if(resp.isPresent()) {
			Profesor profesor = resp.get();
			
			profesorRepositorio.delete(profesor);
		} else {
			throw new WebException("El rofesor no esta regstrado en la base de datos");
		}
	}
	
	
	//metodo para dar de baja un profesor
	@Transactional
	public void darBajaAlta(String id) throws WebException {
		//validamos el string
		validarString(id);
		
		//comprobamos si esta en la base de datos
		Optional<Profesor> resp = profesorRepositorio.findById(id);
		
		if(resp.isPresent()) {
			Profesor profesor = resp.get();
			
			if(profesor.isAlta()) {
				profesor.setAlta(false);
			}else {
				profesor.setAlta(true);
			}
			
		}else {
			throw new WebException("El profesor no exste en la base de datos");
		}
	}
	
	//Metodo para borrar un curso de un profesor
	@Transactional
	public void borrarCurso(String idProfesor, String idCurso) throws WebException {
		Profesor profesor = new Profesor();
		
		//valdamos que no esten vacios
		validarString(idCurso);
		validarString(idProfesor);
		
		//comrobamos s esta en  la basse de datos
		Optional<Profesor> resp = profesorRepositorio.findById(idProfesor);
		if(resp.isEmpty()) {
			throw new WebException("El profesor no esta en la base de datos");
		}else {
			profesor = resp.get();
			
			List<Curso> cursos = profesor.getCursos();
			cursos.remove(cursoServicio.buscarPorId(idCurso));
			profesor.setCursos(cursos);
			
			profesorRepositorio.save(profesor);
		}
	}
	
	//sumar un curso a un profesor
	@Transactional
	public void sumarCurso(String idProfesor, String idCurso) throws WebException {
		Profesor profesor = new Profesor();
		
		//valdamos que no esten vacios
		validarString(idCurso);
		validarString(idProfesor);
		
		//comrobamos s esta en  la basse de datos
		Optional<Profesor> resp = profesorRepositorio.findById(idProfesor);
		if(resp.isEmpty()) {
			throw new WebException("El profesor no esta en la base de datos");
		}else {
			profesor = resp.get();
			
			List<Curso> cursos = profesor.getCursos();
			cursos.add(cursoServicio.buscarPorId(idCurso));
			profesor.setCursos(cursos);
			
			profesorRepositorio.save(profesor);
		}
	}
	
	//metodo para dvolver un profesor
	public Profesor buscarPorId(String id) throws WebException {
		Optional<Profesor> resp = profesorRepositorio.findById(id);
		
		if(resp.isEmpty()) {
			throw new WebException("no se encuentra en la base de datos");
		}else {
			Profesor profesor = resp.get();
			
			return profesor;
		}
	}
	
	//metodo para valdar los string
	public void validarString(String texto) throws WebException {
		if(texto.isBlank()) {
			throw new WebException("Uno/s campo/s esta/n vacio/s");
		}
	}
	
	//metodo para validar si el dto tiene los campos completos
	@Transactional
	public void validarDTO(ProfesorDTO profesorDTO) throws WebException {
		if(profesorDTO.getNombre() == null || profesorDTO.getNombre().equals("")) {
			throw new WebException("Debe ingresar un nombre");
		}
		
		if(profesorDTO.getApellido() == null || profesorDTO.getApellido().equals("")) {
			throw new WebException("Debe ingresar un apellido");
		}
	}
	
	//metodo para devolver la lista con todos los profesores
	public List<Profesor> listaProfesor(){
		List<Profesor> profesors = profesorRepositorio.findAll();
		
		return profesors;
	}
	
	//metodo para convertir un dto a entidad
	public Profesor dtoEntidad(ProfesorDTO profesorDTO) {
		Profesor profesor = new Profesor();
		
		profesor.setNombre(profesorDTO.getNombre());
		profesor.setApellido(profesorDTO.getApellido());
		
		if(profesorDTO.getCursos().isEmpty()) {
			profesor.setCursos(new ArrayList<>());
		}else {
			profesor.setCursos(profesorDTO.getCursos());
		}
		
		if(!profesorDTO.getId().isBlank()) {
			profesor.setId(profesorDTO.getId());
		}
		
		return profesor;
	}
}
