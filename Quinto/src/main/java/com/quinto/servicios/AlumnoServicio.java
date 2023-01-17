package com.quinto.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quinto.dto.AlumnoDTO;
import com.quinto.dto.CursoDTO;
import com.quinto.entidades.Alumno;
import com.quinto.entidades.Curso;
import com.quinto.excepciones.WebException;
import com.quinto.repositorios.AlumnoRepositorio;
import com.quinto.repositorios.CursoRepositorio;
import com.quinto.utilidades.Conversores;

import jakarta.transaction.Transactional;

@Service
public class AlumnoServicio {
	/*
	@Autowired
	private ModelMapper modelMapper;*/
	
	private Conversores c1 = new Conversores();

	@Autowired
	private AlumnoRepositorio alumnoRepositorio;
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	//metodo para registrar un nuevo alumno
	@Transactional
	public Alumno crearAlumno(String nombre, String mail, String eda, String historia, LocalDate fecha, String clave ) throws WebException {
		Alumno alumno = new Alumno();
		
		//validamos que no este vacio
		validarAlumno(nombre, mail, mail, eda, historia, fecha);
		
		//comprobamos que no se encuentre en la base de datos
		Optional<Alumno> resp = alumnoRepositorio.buscarPorCorreo(mail);
		
		if(resp.isPresent()) {
			alumno = resp.get();
			
			throw new WebException("El alumno ya se encuentra en la base de datos");
		}else {
			
			
			alumno.setNombre(nombre);
			alumno.setEdad(c1.aEntero(eda));
			alumno.setCursos(new ArrayList<>());
			alumno.setFechaNacimiento(fecha);
			alumno.setHistoria(historia);
			alumno.setAlta(true);
			alumno.setClave(clave);
			
			alumnoRepositorio.save(alumno);
			
			return alumno;
		}
	}
	
	//actualizar los datos de alumno
	@Transactional
	public Alumno actualizarAlumno(String id, String nombre, String mail, String correo, String eda, String historia, LocalDate fecha, String clave) throws WebException {
		Alumno alumno = new Alumno();
		//validamos los datos
		validarAlumno(nombre, mail, correo, eda, historia, fecha);
		
		//buscamos si existe en la base de datos
		Optional<Alumno> resp = alumnoRepositorio.findById(id);
		
		if(resp.isPresent()) {
			alumno = resp.get();
			
			//modificamos los valores
			alumno.setNombre(nombre);
			alumno.setEdad(c1.aEntero(eda));
			alumno.setCursos(new ArrayList<>());
			alumno.setFechaNacimiento(fecha);
			alumno.setHistoria(historia);
			alumno.setAlta(true);
			alumno.setClave(clave);
			
			alumnoRepositorio.save(alumno);
			
			
		}else {
			throw new WebException("El alumno no exste en la base de datos");
		}
		
		return alumno;
	}
	
	//eliminar un alumnoo de la base de datos
	@Transactional
	public void eliminarAlumno(String id) throws WebException {
		Alumno alumno = new Alumno();
		
		//comprobamos s exste en la base de datos
		Optional<Alumno> resp = alumnoRepositorio.findById(id);
		
		if(resp.isPresent()) {
			alumno = resp.get();
			
			alumnoRepositorio.delete(alumno);
		}else {
			throw new WebException("El alumno no esta en la base de datos");
		}
	}
	
	//dar de baja o de alta un alumno
	@Transactional
	public void darAltaBaja(String id) throws WebException {
		Alumno alumno = new Alumno();
		
		//comprobamos en la base de datos
		Optional<Alumno> resp = alumnoRepositorio.findById(id);
		
		if(resp.isPresent()) {
			alumno = resp.get();
			
			if(alumno.isAlta()) {
				alumno.setAlta(false);
			}else {
				alumno.setAlta(true);
			}
		} else {
			throw new WebException("El alumno no existe en la base de datos");
		}
	}
	
	//agregar un curso a un alumno
	@Transactional
	public void agregarCurso(String idAlumno, String idCurso) throws WebException {
		Alumno alumno = new Alumno();
		Curso curso = new Curso();
		
		//comprobamos si esta en la base de datos
		Optional<Alumno> resp = alumnoRepositorio.findById(idAlumno);
		
		Optional<Curso> respCurso = cursoRepositorio.findById(idCurso);
		
		if(resp.isPresent() && respCurso.isPresent()) {
			alumno = resp.get();
			curso = respCurso.get();
			
			alumno.getCursos().add(curso);
			alumnoRepositorio.save(alumno);
		}else {
			throw new WebException("El alumno o el curso no se encuentra en la base de datos");
		}
	}
	
	//quitar un curso de la lista de cursos del alumno
	@Transactional
	public void quitarCurso(String idCurso, String idAlumno) throws WebException {
		Alumno alumno = new Alumno();
		Curso curso = new Curso();
		
		//comprobamos si esta en la base de datos
		Optional<Alumno> resp = alumnoRepositorio.findById(idAlumno);
		
		Optional<Curso> respCurso = cursoRepositorio.findById(idCurso);
		
		if(resp.isPresent() && respCurso.isPresent()) {
			alumno = resp.get();
			curso = respCurso.get();
			
			alumno.getCursos().remove(curso);
			alumnoRepositorio.save(alumno);
		}else {
			throw new WebException("El alumno o el curso no se encuentra en la base de datos");
		}
	}
	
	//devolver la lista de todos los alumnos
	@Transactional
	public List<Alumno> listaAlumno(){
		List<Alumno> alumnos = alumnoRepositorio.findAll();
		
		return alumnos;
	}
	
	//devolver un alumno por el id
	@Transactional
	public Alumno buscarPorId(String id) throws WebException {
		Optional<Alumno> resp = alumnoRepositorio.findById(id);
		
		if(resp.isPresent()) {
			Alumno alumno = resp.get();
			
			return alumno;
		}else {
			throw new WebException("no se encuentra en la base de datos");
		}
	}
	
	
	
	//validar los datos del alumno
	public void validarAlumno(String nombre, String mail, String correo, String eda, String historia, LocalDate fecha) throws WebException {
		
		if(nombre.isBlank()) {
			throw new WebException("El nombre esta vacio");
		}
		
		if(eda.isBlank()) {
			throw new WebException("La edad esta vacia");}
		
		if(correo.isBlank()) {
			throw new WebException("El correo esta vacio");
		}
		
		if(fecha == null) {
			throw new WebException("Ingrese una fecha de nacimiento");
		}
		
		if(historia.isBlank()) {
			throw new WebException("Cuentanos un poco sobre ti");
		}
	}
	
	/*
	//convertir dto a entidad
	public Alumno dtoAEntidad(AlumnoDTO alumnoDTO){
		Alumno alumno = modelMapper.map(alumnoDTO, Alumno.class);
		
		return alumno;
	}
	
	//convertir entidad a dto
	public AlumnoDTO entidadAdto(Alumno alumno) {
		AlumnoDTO alumnoDTO = modelMapper.map(alumno, AlumnoDTO.class);
		
		return alumnoDTO;
	}
	**/
}
