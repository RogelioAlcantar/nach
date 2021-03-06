package com.example.examen.back.end.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.examen.back.end.entidades.Automovil;
import com.example.examen.back.end.repositorios.AutomovilRepository;

@Service
public class AutomovilServiceImple implements AutomovilService{

	@Autowired
	private AutomovilRepository automovilRepository; 
	
	@Override
	public List<Automovil> reporteEmpresa() {
		return automovilRepository.reporteEmpresa();
	}

	@Override
	public ResponseEntity<Object> createAutomovil(Automovil automovil) {
		if(automovilRepository.findById(automovil.getId_automovil()) == null)
			return new ResponseEntity<>("El automovil ya existe en la base de datos", HttpStatus.BAD_REQUEST);;
		return new ResponseEntity<>(automovilRepository.save(automovil), HttpStatus.OK);
	}

	@Override
	public Automovil reporteResidente(String placa) {
		return automovilRepository.reporteResidente(placa);
	}

	@Override
	public Automovil findAutomovil(String placa) {
		return automovilRepository.findByPlaca(placa);
	}

}
