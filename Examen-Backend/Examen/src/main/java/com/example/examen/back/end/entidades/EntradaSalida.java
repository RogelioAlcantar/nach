package com.example.examen.back.end.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.sun.istack.Nullable;

import io.swagger.annotations.ApiModelProperty;

/*
 * Clase que representa una tabla en la BD llamada entradasalida, con esto podemos generar el ORM correcto
 */
@Entity
@Table(name = "entradasalida")
public class EntradaSalida implements Serializable{
	
	@ApiModelProperty(value = "es el id de nuestra tabla")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_entradasalida;

	@ApiModelProperty(value = "las placas del automovil")
	@Column(length = 30)
	private String placa;
	
	@ApiModelProperty(value = "Fecha y hora de llegada")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date llegada;
	
	@ApiModelProperty(value = "Fecha y hora de salida")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date salida;
	
	@ApiModelProperty(value = "precio de la tarifa a cobrar")
	@Nullable
	private Double tarifa;
	
	@ApiModelProperty(value = "total a pagar")
	@Nullable
	private Double total;
	
	@ApiModelProperty(value = "id del automovil asociado")
	@ManyToOne
	private Automovil automovil;
	
	public Long getId_entradasalida() {
		return id_entradasalida;
	}

	public void setId_entradasalida(Long id_entradasalida) {
		this.id_entradasalida = id_entradasalida;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getLlegada() {
		return llegada;
	}

	public void setLlegada(Date llegada) {
		this.llegada = llegada;
	}

	public Date getSalida() {
		return salida;
	}

	public void setSalida(Date salida) {
		this.salida = salida;
	}
	
	public Automovil getAutomovil() {
		return automovil;
	}

	public void setAutomovil(Automovil automovil) {
		this.automovil = automovil;
	}

	
	public Double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}



	private static final long serialVersionUID = 1L;
	
}
