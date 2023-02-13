package com.quinto.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quinto.entidades.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, String>{

	@Query("SELECT c FROM Curso c WHERE c.nombre = :nombre")
	public Optional<Curso> buscarPorNombre(@Param("nombre")String nombre);
	
	@Query("SELECT c from Curso c WHERE c.profesor IS NULL")
	public Optional<List<Curso>> cursosnProfesor();
	
	@Query("SELECT c from Curso c WHERE c.alumnos <> :idAlumno")
	public Optional<List<Curso>> cursoDistinto(@Param("idAlumno") String idAlumno);
	
	/*@Query("SELECT c FROM Curso c LIMIT 3")
	public Optional<List<Curso>> tresCursos();*/
}
