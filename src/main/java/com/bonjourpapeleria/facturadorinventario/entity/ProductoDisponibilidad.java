package com.bonjourpapeleria.facturadorinventario.entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
@Entity
@Table(name= "producto_disponibilidad")
public class ProductoDisponibilidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "id_disponibilidad")
	private Long idDisponibilidad;
	
	

	@Column(name = "stock_producto")
	private Long stockProducto;
	
	 @OneToOne(fetch = FetchType.LAZY)
	 @JsonIgnore
	 @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
	 private Producto producto;
	 
	 @JsonIgnore
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
	 private Categoria categoria;

	public Long getIdDisponibilidad() {
		return idDisponibilidad;
	}

	public void setIdDisponibilidad(Long idDisponibilidad) {
		this.idDisponibilidad = idDisponibilidad;
	}

	public Long getStockProducto() {
		if(this.stockProducto==null)
		{stockProducto = 0L;

		}

		return stockProducto;
	}

	public void setStockProducto(Long stockProducto) {
		this.stockProducto = stockProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
