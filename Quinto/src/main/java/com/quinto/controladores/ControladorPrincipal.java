package com.quinto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quinto.servicios.CursoServicio;
import com.quinto.servicios.ProfesorServicio;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {
	
	@Autowired
	private CursoServicio cursoServicio;
	
	@Autowired
	private ProfesorServicio profesorServicio;

	@GetMapping("/")
	public String index(ModelMap cursos, ModelMap profesores) {
		try {
			cursos.addAttribute("cursos", cursoServicio.TodosLosCursos());
			profesores.addAttribute("profesores", profesorServicio.listaProfesor());
		} catch (Exception e) {
			cursos.addAttribute("error", e.getMessage());
		}
		
		return "index.html";
	}
	
	
}
