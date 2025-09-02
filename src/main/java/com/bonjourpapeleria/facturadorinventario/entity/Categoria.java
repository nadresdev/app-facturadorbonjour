package com.bonjourpapeleria.facturadorinventario.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;


@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {






	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_categoria;
	@Column(name = "cod_categoria")
	private String codCategoria;
	private String nombre;
	@Column(name = "porcentaje_beneficio_categoria")
	private Long procentajeBeneficio;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/mm/yy")
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@JsonIgnore
	@OneToMany(mappedBy = "categoriaproductos",fetch = FetchType.LAZY)
	private List<Producto> productos;
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private List<ProductoDisponibilidad> disponibilidadCategoria;
	
	
	
	public Long getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}

	public List<ProductoDisponibilidad> getDisponibilidadCategoria() {
		return disponibilidadCategoria;
	}

	public void setDisponibilidadCategoria(List<ProductoDisponibilidad> disponibilidadCategoria) {
		this.disponibilidadCategoria = disponibilidadCategoria;
	}

	@PrePersist
	public void prePersist() {
		fechaCreacion= new Date();
		
	}
	
	public Categoria() {
		productos = new ArrayList<Producto>();
		disponibilidadCategoria = new ArrayList<ProductoDisponibilidad>();
		};
		
	public void addProducto(Producto producto) {
		
		this.productos.add(producto);
	}	
		
	public void addProductoDisponibilidad(ProductoDisponibilidad disponibilidadCategoria) {
		
		this.disponibilidadCategoria.add(disponibilidadCategoria);
	}	
		
		
	
	
	public Long getIdcategoria() {
		return id_categoria;
	}


	public void setIdcategoria(Long idcategoria) {
		this.id_categoria = idcategoria;
	}


	public String getCodCategoria() {
		return codCategoria;
	}


	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Long getProcentajeBeneficio() {
		return procentajeBeneficio;
	}


	public void setProcentajeBeneficio(Long procentajeBeneficio) {
		this.procentajeBeneficio = procentajeBeneficio;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
