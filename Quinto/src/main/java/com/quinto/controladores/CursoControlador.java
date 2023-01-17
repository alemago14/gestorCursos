package com.quinto.controladores;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quinto.entidades.Curso;
import com.quinto.entidades.Profesor;
import com.quinto.excepciones.WebException;
import com.quinto.servicios.CursoServicio;
import com.quinto.servicios.ProfesorServicio;

@Controller
@RequestMapping("/curso")
public class CursoControlador {

	@Autowired
	private CursoServicio cursoServicio;
	
	@Autowired
	private ProfesorServicio profesorServicio;
	
	@GetMapping("/")
	public String cursoIndex(ModelMap modelo) {
		List<Profesor> profesores = profesorServicio.listaProfesor();
		modelo.addAttribute("profesores", profesores);
		
		return "registroCurso.html";
	}
	
	@PostMapping("/registro")
	public String registroCurso(ModelMap modelo, ModelMap modelo2 ,@RequestParam String nombre, @RequestParam String turno, @RequestParam LocalTime horarioI, @RequestParam LocalTime horarioF, @RequestParam String profesor) {
		try {
			cursoServicio.crearCurso(nombre, turno, horarioI, horarioF, profesor);
			modelo.put("exito", "Registro completado");
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
		}
		
		List<Profesor> profesores = profesorServicio.listaProfesor();
		modelo2.addAttribute("profesores", profesores);
		return "registroCurso.html";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap modelo) {
		List<Curso> cursos = cursoServicio.TodosLosCursos();
		modelo.addAttribute("cursos", cursos);
		
		return "listaCurso.html";
	}
	
	@GetMapping("/mod/{id}")
	public String modificacion(ModelMap modelo, ModelMap modelo2,@PathVariable String id) {
		try {
			Curso curso = cursoServicio.buscarPorId(id);
			
			modelo.addAttribute("curso", curso);
		} catch (WebException e) {
			// TODO Auto-generated catch block
			modelo.put("error", e.getMessage());
		}
		List<Profesor> profesores = profesorServicio.listaProfesor();
		modelo2.addAttribute("profesores", profesores);
		
		return "modCurso.html";
	}
	
	@PostMapping("/modficar")
	public String actualizarCurso(ModelMap modelo, ModelMap modelo2, @RequestParam String id,@RequestParam String nombre, @RequestParam String turno, @RequestParam LocalTime horarioI, @RequestParam LocalTime horarioF, @RequestParam String profesor) {
		try {
			cursoServicio.modificarCurso(id, nombre, turno, horarioI, horarioF, profesor);
			modelo.put("exito", "Registro completado");
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		
		modelo2.addAttribute("cursos", cursoServicio.TodosLosCursos());
		
		return "listaCurso.html";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarCurso(ModelMap modelo, ModelMap modelo2, @PathVariable String id) {
		try {
			cursoServicio.eliminarCurso(id);
			modelo.put("exito", "Borrado completado");
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		
		modelo2.addAttribute("cursos", cursoServicio.TodosLosCursos());
		
		return "listaCurso.html";
	}
}
