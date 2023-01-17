package com.quinto.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quinto.entidades.Alumno;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, String> {

	@Query("SELECT a FROM Alumno a WHERE a.nombre = :nombre")
	public Optional<Alumno> buscarPorNombre(@Param("nombre")String nombre);
	
	@Query("SELECT a FROM Alumno a WHERE a.mail = :mail")
	public Optional<Alumno> buscarPorCorreo(@Param("mail") String mail);
}
