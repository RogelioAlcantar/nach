package com.example.examen.back.end.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

/*
 * Clase que representa una tabla en la BD llamada automoviles, con esto podemos generar el ORM correcto
 */
@Entity
@Table(name = "automoviles")
public class Automovil implements Serializable {

	@ApiModelProperty(value = "Id de nuestro automovil")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_automovil;

	@ApiModelProperty(value = "placas del automovil")
	@Column(length = 30, unique = true)
	private String placa;

	@ApiModelProperty(value = "modelo del automovil")
	private String modelo;

	@ApiModelProperty(value = "anio del automovil")
	private Long anio;

	@ApiModelProperty(value = "marca del automovil")
	private String marca;

	@ApiModelProperty(value = "tipo del automovil, E = empresa y R = residente")
	@Column(length = 1)
	private String tipo_auto;

	@ApiModelProperty(value = "color del automovil")
	private String color;

	

	public Long getId_automovil() {
		return id_automovil;
	}

	public void setId_automovil(Long id_automovil) {
		this.id_automovil = id_automovil;
	}


	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipo_auto() {
		return tipo_auto;
	}

	public void setTipo_auto(String tipo_auto) {
		this.tipo_auto = tipo_auto;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	

	private static final long serialVersionUID = 1L;

}
