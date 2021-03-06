package com.example.examen.back.end.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.examen.back.end.entidades.Automovil;

@Repository
public interface AutomovilRepository extends JpaRepository<Automovil, Long>{
	
	@Query("select a from Automovil a where a.tipo_auto = 'E'")
	public List<Automovil> reporteEmpresa();
	
	@Query("select a from Automovil a where a.placa = ?1 and a.tipo_auto = 'R'")
	public Automovil reporteResidente(String placa);
	
	Automovil findByPlaca(String placa);
}
