package com.example.examen.back.end.controladores;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.examen.back.end.entidades.Automovil;
import com.example.examen.back.end.entidades.EntradaSalida;
import com.example.examen.back.end.servicios.AutomovilService;
import com.example.examen.back.end.servicios.EntradaSalidaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/*
 * Clase que representa nuestro controlador
 */
@RestController
@Api(value = "API para el examen")
public class AutomovilController {
	
	/*
	 * Inyectamos el objeto AutomovilService, el cual nos sirve para hacer la presitencia con la BD y la entidad Automovil
	 */
	@Autowired
	private AutomovilService automovilService;
	
	/*
	 * Inyectamos el objeto EntradaSalidaService, el cual nos sirve para hacer la presitencia con la BD y la entidad EntradaSalida
	 */
	@Autowired
	private EntradaSalidaService entradaSalidaService;
	
	@ApiOperation(value = "Retorna todos los automoviles que son de EMPRESA")
	@GetMapping("/reporte/automovil/empresa")
	public List<Automovil> reporteEmpresa(){
		return automovilService.reporteEmpresa();
	}
	
	@ApiOperation(value = "Retorna un automovil RESIDENTE")
	@GetMapping("/reporte/automovil/residente/{placa}")
	public Automovil reporteResidente(@PathVariable String placa){
		return automovilService.reporteResidente(placa);
	}
	
	@ApiOperation(value = "Crea un automovil RESIDENTE")
	@PostMapping("/crear/residente")
	public ResponseEntity<Object> crearResidente(@Validated @RequestBody Automovil automovil){
		if(automovil.getTipo_auto().equals("R")) {
			return automovilService.createAutomovil(automovil);
		}else {
			return new ResponseEntity<>("El automovil no es residente, favor de verificar", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ApiOperation(value = "Crea un automovil EMPRESA")
	@PostMapping("/crear/empresa")
	public ResponseEntity<Object> crearEmpresa(@Validated @RequestBody Automovil automovil){
		if(automovil.getTipo_auto().equals("E")) {
			return automovilService.createAutomovil(automovil);
		}else {
			return new ResponseEntity<>("El automovil no es de empresa, favor de verificar", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ApiOperation(value = "Busca un automovil dado su id")
	@GetMapping("/buscar/ES/{id}")
	public EntradaSalida buscarES(@PathVariable Long id) {
		return entradaSalidaService.buscarES(id);
	}
	
	@ApiOperation(value = "Crea una entrada de un automovil, este automovil debe estar previamene cargado en su tabla correspondiente")
	@PostMapping("/crear/entrada/automovil")
	public ResponseEntity<Object> crearEntrada(@Validated @RequestBody EntradaSalida es){
		if(es.getAutomovil() == null) {
			return new ResponseEntity<>("Para generar una entrada debe ir asociada a un automovil", HttpStatus.BAD_REQUEST);
		}
		return entradaSalidaService.createEntradaSalida(es);
	}
	
	@ApiOperation(value = "Regresa el cobro de un automovil, sea de EMPRESA o RESIDENTE")
	@GetMapping("/genera/salida/{placa}")
	public ResponseEntity<Object> contarES(@PathVariable String placa){
		Automovil auto = automovilService.findAutomovil(placa);
		EntradaSalida es = entradaSalidaService.salida(placa);
		if(auto != null && es != null) {
			if(auto.getTipo_auto().equals("E")) {
				es.setSalida(new Date());
				es.setTarifa(0.6);
				long diferencia = es.getSalida().getTime() - es.getLlegada().getTime();
				long diffMinutes = diferencia / (60 * 1000);
				es.setTotal(diffMinutes * es.getTarifa());
				entradaSalidaService.actualizaSalida(placa, es);
				return new ResponseEntity<>(es, HttpStatus.OK);
			}else {
				int veces = entradaSalidaService.contarES(placa);
				if(veces > 10) {
					es.setSalida(new Date());
					double descuento =  0.6 * 0.2;
					es.setTarifa(0.6 - descuento);
					long diferencia = es.getSalida().getTime() - es.getLlegada().getTime();
					long diffMinutes = diferencia / (60 * 1000);
					es.setTotal(diffMinutes * es.getTarifa());
					entradaSalidaService.actualizaSalida(placa, es);
					return new ResponseEntity<>(es, HttpStatus.OK);
				}else if(veces == 20) {
					es.setSalida(new Date());
					es.setTarifa(0.0);
					long diferencia = es.getSalida().getTime() - es.getLlegada().getTime();
					long diffMinutes = diferencia / (60 * 1000);
					es.setTotal(diffMinutes * es.getTarifa());
					entradaSalidaService.actualizaSalida(placa, es);
					return new ResponseEntity<>(es, HttpStatus.OK);
				}else {
					es.setSalida(new Date());
					es.setTarifa(0.6);
					long diferencia = es.getSalida().getTime() - es.getLlegada().getTime();
					long diffMinutes = diferencia / (60 * 1000);
					es.setTotal(diffMinutes * es.getTarifa());
					entradaSalidaService.actualizaSalida(placa, es);
					return new ResponseEntity<>(es, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<>("Ocurrio un error a la hora de salir del auto con placa: " + placa, HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "Regresa el reporte de un automovil, este debe ser de tipo EMPRESA")
	@GetMapping("/reporte/ES/empresa/{placa}")
	public ResponseEntity<Object> reporteESEmpresa(@PathVariable String placa){
		List<EntradaSalida> lista = entradaSalidaService.reporteESEmpresa(placa);
		if(lista.isEmpty()) {
			return new ResponseEntity<>("No se encontro nada en la base de datos con la "  + placa + " o esa placa no es de tipo EMPRESA", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Regresa el reporte de un automovil, este debe ser de tipo RESIDENTE")
	@GetMapping("/reporte/ES/residente/{placa}")
	public ResponseEntity<Object> reporteESResidente(@PathVariable String placa){
		List<EntradaSalida> lista = entradaSalidaService.reporteESResidente(placa);
		if(lista.isEmpty()) {
			return new ResponseEntity<>("No se encontro nada en la base de datos con la placa "  + placa + " o esa placa no es de tipo RESIDENTE", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
}
