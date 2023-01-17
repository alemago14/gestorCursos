package com.quinto.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quinto.entidades.Profesor;

@Repository
public interface ProfesorRepositorio extends JpaRepository<Profesor, String> {

	@Query("SELECT p FROM Profesor p WHERE p.nombre = :nombre AND p.apellido = :apellido")
	public Optional<Profesor> buscarPorNombreApellido(@Param("nombre")String nombre,@Param("apellido")String apellido);
}
