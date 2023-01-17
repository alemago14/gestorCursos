package com.quinto.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quinto.dto.AlumnoDTO;
import com.quinto.entidades.Alumno;
import com.quinto.entidades.Curso;
import com.quinto.entidades.Profesor;
import com.quinto.excepciones.WebException;
import com.quinto.servicios.AlumnoServicio;
import com.quinto.servicios.CursoServicio;
import com.quinto.utilidades.Conversores;

@Controller
@RequestMapping("/alumnos")
public class AlumnoControlador {

	@Autowired
	private CursoServicio cursoServicio;
	
	@Autowired
	private AlumnoServicio alumnoServicio;
	
	private Conversores conversores = new Conversores();
	
	@GetMapping("/nuevo")
	public String alumno() {
		return "alumnos.html";
	}
	
	@PostMapping("/registro")
	public String registrarAlumno(ModelMap modelo, String nombre, String mail, String edad, LocalDate fechaNac, String historia, String clave) throws WebException {
		try {
			alumnoServicio.crearAlumno(nombre, mail, edad, historia, fechaNac, clave);
			modelo.put("exito", "Registro completado");
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
		}
		
		return "alumnos.html";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap modelo) {
		
		try {
			List<Alumno> alumnos = alumnoServicio.listaAlumno();
			
			modelo.addAttribute("alumnos", alumnos);
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
		}
		return "listaAlumnos.html";
	}
	
	@GetMapping("/mod/{id}")
	public String modifcacion(ModelMap modelo, ModelMap modelo2, @PathVariable String id) {
		try {
			Alumno alumno = alumnoServicio.buscarPorId(id);
			modelo2.addAttribute("alumno", alumno);
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}

		return "modAlumno.html";
	}
	
	@PostMapping("/modificar")
	public String modificarAlumno(ModelMap modelo, ModelMap modelo2, String id, String nombre, String mail, String edad, LocalDate fechaNac, String historia, String clave) {
		try {
			alumnoServicio.actualizarAlumno(id, nombre, mail, mail, edad, historia, fechaNac, clave);
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		try {
			List<Alumno> alumnos = alumnoServicio.listaAlumno();
			
			modelo.addAttribute("alumnos", alumnos);
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
		}
		return "listaAlumnos.html";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarAlumno(ModelMap modelo, ModelMap modelo2, @PathVariable String id) {
		try {
			alumnoServicio.eliminarAlumno(id);
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		try {
			List<Alumno> alumnos = alumnoServicio.listaAlumno();
			
			modelo.addAttribute("alumnos", alumnos);
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
		}
		return "listaAlumnos.html";
	}
	
	@GetMapping("/sumarCurso/{id}")
	public String addCursos(ModelMap modelo, ModelMap modelo2, @PathVariable String id) {
		try {
			Alumno alumno = alumnoServicio.buscarPorId(id);
			
			List<Curso> cursos = cursoServicio.cursoAlumno(id);
			
			modelo.addAttribute("cursos", cursos);
			modelo2.addAttribute("alumno", alumno);
		} catch (WebException e) {
			// TODO Auto-generated catch block
			modelo.put("error", e.getMessage());
		}
		
		
		return "inscribirCurso.html";
	}
	
	@PostMapping("/sumar")
	public String sumar(ModelMap modelo, ModelMap modelo2, @RequestParam String idAlumno, @RequestParam String idCurso) {
		
		try {
			alumnoServicio.agregarCurso(idAlumno, idCurso);
			modelo.put("exito", "Registro completado");
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		
		try {
			List<Alumno> alumnos = alumnoServicio.listaAlumno();
			
			modelo.addAttribute("alumnos", alumnos);
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
		}
		return "listaAlumnos.html";
	}
	
	@GetMapping("/restarCurso/{id}")
	public String resCursos(ModelMap modelo, ModelMap modelo2, @PathVariable String id) {
		try {
			Alumno alumno = alumnoServicio.buscarPorId(id);
			modelo2.addAttribute("alumno", alumno);
		} catch (WebException e) {
			// TODO Auto-generated catch block
			modelo.put("error", e.getMessage());
		}
		
		
		return "bajarCurso.html";
	}
	
	@PostMapping("/restar")
	public String restar(ModelMap modelo, ModelMap modelo2, @RequestParam String idAlumno, @RequestParam String idCurso) {
		
		try {
			alumnoServicio.quitarCurso(idCurso, idAlumno);
			modelo.put("exito", "Registro completado");
		} catch (WebException e) {
			modelo.put("error", e.getMessage());
		}
		
		try {
			List<Alumno> alumnos = alumnoServicio.listaAlumno();
			
			modelo.addAttribute("alumnos", alumnos);
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
		}
		return "listaAlumnos.html";
	}
	
	@GetMapping("/consulta")
	public String pagconsulta(ModelMap modelo) {
		try {
			List<Alumno> alumnos = alumnoServicio.listaAlumno();
			
			modelo.addAttribute("alumnos", alumnos);
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
		}
		
		return "consulta.html";
	}
	
	@GetMapping("/consultar")
	public String consulta(@RequestParam String texto, @RequestParam String idCurso) {
		
		
		return "consulta.html";
	}
}
