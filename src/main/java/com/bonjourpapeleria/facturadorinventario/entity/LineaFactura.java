package com.bonjourpapeleria.facturadorinventario.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_factura")
public class LineaFactura implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_det_factura")
	private Long id_det_factura;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yy")
	private Date fecha_elaboracion;
	private String observacion;
	private Long id_categoria;
	private String estado;
	private Long cantidad;
	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidadProducto) {
		this.cantidad = cantidadProducto;
	}

	private Long valorImporte;


	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
	private Producto producto;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_factura",referencedColumnName = "id_factura")
	private Factura factura;
	
	@PrePersist
	public void prePersist() {
		fecha_elaboracion= new Date();
		
	}
	
	public Long calcularTotalProducto() {
		
	
		return cantidad * producto.getPrecioBeneficio() ;
		
		
	}

	public Long getId_det_factura() {
		return id_det_factura;
	}

	public void setId_det_factura(Long id_det_factura) {
		this.id_det_factura = id_det_factura;
	}

	public Date getFecha_elaboracion() {
		return fecha_elaboracion;
	}

	public void setFecha_elaboracion(Date fecha_elaboracion) {
		this.fecha_elaboracion = fecha_elaboracion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Long getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getvalorImporte() {
		return calcularTotalProducto();
	}

	public void setvalorImporte(Long precio) {
		this.valorImporte = precio;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
