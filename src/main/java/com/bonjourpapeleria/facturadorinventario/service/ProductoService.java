package com.bonjourpapeleria.facturadorinventario.service;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bonjourpapeleria.facturadorinventario.entity.Producto;
import com.bonjourpapeleria.facturadorinventario.entity.Categoria;
import com.bonjourpapeleria.facturadorinventario.entity.Factura;


import com.bonjourpapeleria.facturadorinventario.repository.IProductoRepository;

import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class ProductoService {


	@Autowired
	private IProductoRepository productoRepository;

	@Autowired
	private EntityManager em;


	public Producto guardar(Producto producto) {

		return productoRepository.save(producto);
	}

	public Page<Producto> Listar(Producto producto) {
		final Pageable page = PageRequest.of(0, 10);
		return productoRepository.findAll(page);
	}

	public Producto getProductoXId(Long id) {


		return productoRepository.getOne(id);
	}

	public List<Producto> getProductosXnombre(String coincidencia) {
		List<Producto> recientes = new ArrayList<>();

		try {
			Query query1 = em.createQuery("from Producto p where p.nombre like '" + coincidencia + "'");
			recientes = query1.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return recientes;
	}


	public Page<Producto> getProductosRecientes(Pageable pageN) {
		int pagina = pageN.getPageNumber();
		pageN = (Pageable) PageRequest.of(pagina, pageN.getPageSize()).withSort(Sort.by("idProducto").descending());


		return productoRepository.findAll(pageN);
	}

/*public Page<Producto> getProductosPorCategoriaB(){
	
	final Pageable page = (Pageable) PageRequest.of(0,10);
	 
	
	return  productoRepository.findAll(page);
}*/


	public List<Producto> getProductosPorCategoria(Long idCategoria) {
		List<Producto> recientes = new ArrayList<>();

		try {
			Query query1 = em.createQuery("from Producto JOIN Categoria  where id_categoria = " + idCategoria + "Order By idProducto desc");
			recientes = query1.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return recientes;
	}

	public void borrarProductoXId(Long id) {


		productoRepository.deleteById(id);


	}

}
