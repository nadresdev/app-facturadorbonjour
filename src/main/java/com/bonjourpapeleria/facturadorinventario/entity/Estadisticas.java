package com.bonjourpapeleria.facturadorinventario.entity;

import java.util.Date;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;


@NoArgsConstructor

@Entity
public class Estadisticas {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	private Long id;
	private Long total_beneficio;
	private Long total_inventario;
	private Long total_profit;
	private Long total_egreso;
	private String fecha_objetivo;

	public String getFecha_objetivo() {
		return fecha_objetivo;
	}

	public void setFecha_objetivo(String fecha_objetivo) {
		this.fecha_objetivo = fecha_objetivo;
	}

	public Long getTotal_egreso() {
		return total_egreso;
	}

	public void setTotal_egreso(Long total_egreso) {
		this.total_egreso = total_egreso;
	}

	public Long getTotal_beneficio() {
		return total_beneficio;
	}

	public void setTotal_beneficio(Long total_beneficio) {
		this.total_beneficio = total_beneficio;
	}

	public Long getTotal_inventario() {
		return total_inventario;
	}

	public void setTotal_inventario(Long total_inventario) {
		this.total_inventario = total_inventario;
	}

	public Long getTotal_profit() {
		return total_profit;
	}

	public void setTotal_profit(Long total_profit) {
		this.total_profit = total_profit;
	}

	public Long getCantidad_ventas() {
		return cantidad_ventas;
	}

	public void setCantidad_ventas(Long cantidad_ventas) {
		this.cantidad_ventas = cantidad_ventas;
	}

	private Long cantidad_ventas;
	
	
}
