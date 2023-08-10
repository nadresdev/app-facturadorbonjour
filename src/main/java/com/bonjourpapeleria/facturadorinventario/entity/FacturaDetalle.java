package com.bonjourpapeleria.facturadorinventario.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
@Entity
@Table(name = "factura_detalle")
public class FacturaDetalle implements Serializable {

/**
	 * 
	 */
private static final long serialVersionUID = 1L;


	public Long getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(Long id_detalle) {
		this.id_detalle = id_detalle;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id_detalle;
@Column(name = "fecha_elaboracion")
@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/mm/yy")
private Date fechaElaboracion;
private Long valor;
	@JsonIgnore
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
private Factura factura;


public Date getFechaElaboracion() {
	return fechaElaboracion;
}

public void setFechaElaboracion(Date fechaElaboracion) {
	this.fechaElaboracion = fechaElaboracion;
}

public Long getValor() {
	return valor;
}

public void setValor(Long total) {
	this.valor = total;
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
