package com.bonjourpapeleria.facturadorinventario.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjourpapeleria.facturadorinventario.entity.Producto;
import com.bonjourpapeleria.facturadorinventario.entity.ProductoDisponibilidad;
import com.bonjourpapeleria.facturadorinventario.repository.IProductoDisponibilidad;

@Service
public class ProductoDisponibilidadService {
	
	@Autowired
	private IProductoDisponibilidad productoDisponibilidadRepository;

	public ProductoDisponibilidad guardar(ProductoDisponibilidad productoDisponibilidad) {
		
		return productoDisponibilidadRepository.save(productoDisponibilidad);
	}
	
public List<ProductoDisponibilidad> Listar(ProductoDisponibilidad productoDisponibilidad) {
		
		return productoDisponibilidadRepository.findAll();
	}
public Optional<ProductoDisponibilidad> listarPorId(Long id) {
	
	
	return productoDisponibilidadRepository.findById(id);
}
}
