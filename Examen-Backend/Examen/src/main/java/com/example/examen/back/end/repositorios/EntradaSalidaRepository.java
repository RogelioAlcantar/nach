package com.example.examen.back.end.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.examen.back.end.entidades.EntradaSalida;

@Repository
public interface EntradaSalidaRepository extends JpaRepository<EntradaSalida, Long>{
	
	@Query("select count(es) from EntradaSalida es where es.placa = ?1 and MONTH(es.salida) = MONTH(CURRENT_DATE())")
	public int countES(String placas);
	
	@Query("select es from EntradaSalida es where es.placa = ?1 and es.salida is null")
	public EntradaSalida autoSale(String placas);
	
	@Query("select es from EntradaSalida es join fetch es.automovil a where es.placa = ?1 and a.tipo_auto = 'E'")
	public List<EntradaSalida> reporteESEmpresa(String placa);
	
	@Query("select es from EntradaSalida es join fetch es.automovil a where es.placa = ?1 and a.tipo_auto = 'R'")
	public List<EntradaSalida> reporteESResidente(String placa);
}
