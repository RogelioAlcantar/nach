package com.example.examen.back.end.servicios;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.examen.back.end.entidades.Automovil;

public interface AutomovilService {
	public abstract List<Automovil> reporteEmpresa();
	public abstract Automovil reporteResidente(String placa);
	public abstract ResponseEntity<Object> createAutomovil(Automovil automovil);
	public abstract Automovil findAutomovil(String placa);
}
