package com.bonjourpapeleria.facturadorinventario.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;


@Entity
@Table(name = "facturas")
public class Factura  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_factura")
	private Long idFactura;
	private Long consecutivo;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yy")
	private Date fecha_elaboracion;
	private String estado;
	private Long id_usuario_elabora;
	private String observacion;
	private Long totalFactura;
	private Boolean borrador;
	
	

	public Long getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(Long totalFactura) {
		this.totalFactura = totalFactura;
	}

	public List<LineaFactura> getLineasFactura() {
		return lineasFactura;
	}

	public void setLineasFactura(List<LineaFactura> lineasFactura) {
		this.lineasFactura = lineasFactura;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "factura",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "id_factura",referencedColumnName = "id_factura")
	private List<LineaFactura> lineasFactura;
	
	// mapeo a detalle de factura con valor y fecha no a linea de detalle
	@JsonIgnore
	@OneToOne(mappedBy = "factura",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "id_factura")
	private FacturaDetalle facturaDetalle;

	@PrePersist
	public void prePersist() {
		fecha_elaboracion= new Date();
		this.consecutivo = fecha_elaboracion.getTime();
	}
	

	
	public Factura() {
		
		lineasFactura = new ArrayList<LineaFactura>();
	}
	
	public void addLineaFactura(LineaFactura lineaFactura) {
		
		this.lineasFactura.add(lineaFactura);
	}
	
	
	public Long getTotal() {
		Long total= 0L;
		
		for(int i = 0;i<lineasFactura.size(); i++) {
			total+=lineasFactura.get(i).getvalorImporte();
			
		}
		facturaDetalle.setValor(total);
		this.setTotalFactura(total);
		return total;
		
		
	}
	
	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Date getFecha_elaboracion() {
		return fecha_elaboracion;
	}

	public void setFecha_elaboracion(Date fecha_elaboracion) {
		this.fecha_elaboracion = fecha_elaboracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getId_usuario_elabora() {
		return id_usuario_elabora;
	}

	public void setId_usuario_elabora(Long id_usuario_elabora) {
		this.id_usuario_elabora = id_usuario_elabora;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	public FacturaDetalle getFacturaDetalle() {
		return facturaDetalle;
	}

	public void setFacturaDetalle(FacturaDetalle facturaDetalle) {
		this.facturaDetalle = facturaDetalle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getBorrador() {
		return borrador;
	}

	public void setBorrador(Boolean borrador) {
		this.borrador = borrador;
	}



}
