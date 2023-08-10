package com.bonjourpapeleria.facturadorinventario.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto", unique = true)
	private Long idProducto;
	private String nombre;
	private String marca;
	private String presentacion;
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yy")
	private Date createdAt;
	
	@Column(name = "precio_inventario")
	private Long precioInventario;
	@Column(name = "porcentaje_beneficio_producto")
	private Long beneficioProducto;
	
	private Long precioBeneficio;
	
  @OneToOne(mappedBy = "producto", cascade = CascadeType.REMOVE)
  private ProductoDisponibilidad productoDisponibilidad;

	@JsonIgnore
	@OneToMany(mappedBy = "producto",fetch = FetchType.LAZY)
	//@JoinColumn(name="id_producto",referencedColumnName = "id_producto")
	private List<LineaFactura> lineaFactura;
  @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_categoria",referencedColumnName = "id_categoria")
    private Categoria categoriaproductos;


	@PrePersist
	public void prePersist() {
		createdAt= new Date();
		
	}
	
	public void addLineaFactura(LineaFactura lineaFactura) {
		
		this.lineaFactura.add(lineaFactura);
	}
	
	public Producto() {
		lineaFactura = new ArrayList<LineaFactura>();
	}
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
    
	public Long precioBeneficio() {
		
		Long precioFinal = precioInventario +((precioInventario * this.beneficioProducto)/100);
		precioFinal = precioFinal/100;
		precioFinal = precioFinal*100;
	    this.setPrecioBeneficio(Long.valueOf(precioFinal));
		return precioFinal;
	}
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Long getPrecioInventario() {
		return precioInventario;
	}

	public void setPrecioInventario(Long precioInventario) {
		this.precioInventario = precioInventario;
	}

	public Long getBeneficioProducto() {
		return beneficioProducto;
	}

	public void setBeneficioProducto(Long beneficioProducto) {
		this.beneficioProducto = beneficioProducto;
	}

	public ProductoDisponibilidad getProductoDisponibilidad() {

		return productoDisponibilidad;
	}

	public void setProductoDisponibilidad(ProductoDisponibilidad productoDisponibilidad) {
		this.productoDisponibilidad = productoDisponibilidad;
	}

	public List<LineaFactura> getLineaFactura() {
		return lineaFactura;
	}

	public void setLineaFactura(List<LineaFactura> lineaFactura) {
		this.lineaFactura = lineaFactura;
	}

	public Categoria getCategoria() {
		return categoriaproductos;
	}

	public void setCategoria(Categoria categoria) {
		this.categoriaproductos = categoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getCategoriaId() {
		return getCategoria().getId_categoria();
	}

	public Long getPrecioBeneficio() {
this.precioBeneficio = precioBeneficio();

		return this.precioBeneficio;
	}

	public void setPrecioBeneficio(Long d) {
		this.precioBeneficio = d;
	}

	
}
