package com.quinto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quinto.dto.ProfesorDTO;
import com.quinto.entidades.Curso;
import com.quinto.entidades.Profesor;
import com.quinto.excepciones.WebException;
import com.quinto.servicios.CursoServicio;
import com.quinto.servicios.ProfesorServicio;

@Controller
@RequestMapping("/profesor")
public class ProfesorControlador {

	@Autowired
	private CursoServicio cursoServicio;
	
	@Autowired
	private ProfesorServicio profesorServicio;

	@GetMapping("/")
	public String profesorIndex() {
		return "registroProfesor.html";
	}

	@PostMapping("/registro")
	public String registrar(ModelMap modelo, String nombre, String apellido, String mail) {

		try {
			profesorServicio.crearProfesor(nombre, apellido, mail);
			modelo.put("exito", "Registro completado");
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
		}
		return "registroProfesor.html";
	}

	@GetMapping("/listar")
	public String listarProfesores(ModelMap modelo) {
		List<Profesor> profesores = profesorServicio.listaProfesor();

		modelo.addAttribute("profesores", profesores);

		return "listaProfesor.html";
	}

	@GetMapping("/mod/{id}")
	public String modifcacion(ModelMap modelo, ModelMap modelo2, @PathVariable String id) {
		try {
			Profesor profesor = profesorServicio.buscarPorId(id);
			modelo2.addAttribute("profesor", profesor);
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}

		return "modProfesor.html";
	}

	@PostMapping("/modificar")
	public String modificarProfesor(ModelMap modelo, ModelMap modelo2, String id, String nombre, String apellido,
			String mail) {
		try {
			profesorServicio.actualizarProfesor(id, nombre, apellido, mail);
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		List<Profesor> profesores = profesorServicio.listaProfesor();

		modelo2.addAttribute("profesores", profesores);

		return "listaProfesor.html";
	}

	@GetMapping("/borrar/{id}")
	public String borrarProfesor(ModelMap modelo, ModelMap modelo2, @PathVariable String id) {
		try {
			profesorServicio.eliminarProfesor(id);
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		List<Profesor> profesores = profesorServicio.listaProfesor();

		modelo2.addAttribute("profesores", profesores);

		return "listaProfesor.html";
	}
	
	@GetMapping("/sumarCurso/{id}")
	public String addCursos(ModelMap modelo, ModelMap modelo2, @PathVariable String id) {
		try {
			Profesor profesor = profesorServicio.buscarPorId(id);
			
			List<Curso> cursos = cursoServicio.cursoSinProfesor();
			
			modelo.addAttribute("cursos", cursos);
			modelo2.addAttribute("profesor", profesor);
		} catch (WebException e) {
			// TODO Auto-generated catch block
			modelo.put("error", e.getMessage());
		}
		
		
		return "sumarCurso.html";
	}
	
	@PostMapping("/sumar")
	public String sumar(ModelMap modelo, ModelMap modelo2, @RequestParam String idProfesor, @RequestParam String idCurso) {
		
		try {
			profesorServicio.sumarCurso(idProfesor, idCurso);
			modelo.put("exito", "Registro completado");
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		
		List<Profesor> profesores = profesorServicio.listaProfesor();

		modelo2.addAttribute("profesores", profesores);
		
		return "listaProfesor.html";
	}
	
	@GetMapping("/restarCurso/{id}")
	public String resCursos(ModelMap modelo, ModelMap modelo2, @PathVariable String id) {
		try {
			Profesor profesor = profesorServicio.buscarPorId(id);
			modelo2.addAttribute("profesor", profesor);
		} catch (WebException e) {
			// TODO Auto-generated catch block
			modelo.put("error", e.getMessage());
		}
		
		
		return "restarCurso.html";
	}
	
	@PostMapping("/restar")
	public String restar(ModelMap modelo, ModelMap modelo2, @RequestParam String idProfesor, @RequestParam String idCurso) {
		
		try {
			profesorServicio.borrarCurso(idProfesor, idCurso);
			modelo.put("exito", "Registro completado");
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		
		List<Profesor> profesores = profesorServicio.listaProfesor();

		modelo2.addAttribute("profesores", profesores);
		
		return "listaProfesor.html";
	}
}
