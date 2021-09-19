package com.example.examen.back.end.servicios;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.examen.back.end.entidades.EntradaSalida;

public interface EntradaSalidaService {
	public abstract int contarES(String placa);
	public abstract void actualizaSalida(String placa, EntradaSalida es);
	public abstract ResponseEntity<Object> createEntradaSalida(EntradaSalida es);
	public abstract EntradaSalida salida(String placa);
	public abstract EntradaSalida buscarES(Long id);
	public abstract List<EntradaSalida> reporteESEmpresa(String placa);
	public abstract List<EntradaSalida> reporteESResidente(String placa);
}
