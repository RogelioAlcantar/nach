package com.example.examen.back.end.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.examen.back.end.entidades.Automovil;
import com.example.examen.back.end.entidades.EntradaSalida;
import com.example.examen.back.end.repositorios.EntradaSalidaRepository;

@Service
public class EntradaSalidaServiceImple implements EntradaSalidaService{
	
	@Autowired 
	private EntradaSalidaRepository entradaSalidaRepository;
	
	@Override
	public int contarES(String placa) {
		return entradaSalidaRepository.countES(placa);
	}

	@Override
	public void actualizaSalida(String placa, EntradaSalida es) {
		entradaSalidaRepository.save(es);
	}

	@Override
	public ResponseEntity<Object> createEntradaSalida(EntradaSalida es) {
		return new ResponseEntity<>(entradaSalidaRepository.save(es), HttpStatus.OK);
	}

	@Override
	public EntradaSalida salida(String placa) {
		return entradaSalidaRepository.autoSale(placa);
	}

	@Override
	public EntradaSalida buscarES(Long id) {
		return entradaSalidaRepository.findById(id).orElse(null);
	}

	@Override
	public List<EntradaSalida> reporteESEmpresa(String placa) {
		return entradaSalidaRepository.reporteESEmpresa(placa);
	}

	@Override
	public List<EntradaSalida> reporteESResidente(String placa) {
		return entradaSalidaRepository.reporteESResidente(placa);
	}


}
