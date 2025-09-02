package com.bonjourpapeleria.facturadorinventario.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjourpapeleria.facturadorinventario.entity.Categoria;
import com.bonjourpapeleria.facturadorinventario.entity.Producto;
import com.bonjourpapeleria.facturadorinventario.entity.ProductoDisponibilidad;
import com.bonjourpapeleria.facturadorinventario.repository.IProductoDisponibilidad;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class ProductoDisponibilidadService {
	@Autowired
	private EntityManager em;
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

public Optional<ProductoDisponibilidad> listarPorCategoria(Long id) {
	
	
	return productoDisponibilidadRepository.findById(id);
}
public List<Producto> getProductosPorCategoria(Long idCategoria) {
	List<Producto> recientes = new ArrayList<Producto>();

	try {
		Query query1 = em.createQuery("from Producto  JOIN Categoria  where id_categoria = " + idCategoria + "Order By id_categoria desc");
		recientes = query1.getResultList();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	return recientes;
}



}
