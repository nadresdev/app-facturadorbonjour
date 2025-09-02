package com.bonjourpapeleria.facturadorinventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bonjourpapeleria.facturadorinventario.entity.Categoria;

import com.bonjourpapeleria.facturadorinventario.entity.Movimientos;

import com.bonjourpapeleria.facturadorinventario.repository.IMovimientos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class MovimientosService {

	
	@Autowired
	private EntityManager em;
	
	
	@Autowired
	private IMovimientos iMovimientos;
	
	
	public void borrarXId(Long id ) {
		
		iMovimientos.deleteById(id);
	}
	
	public Page<Movimientos> getTodosMovimientos(Pageable pageN) {
		int pagina = pageN.getPageNumber();
		pageN = PageRequest.of(pagina, pageN.getPageSize()).withSort(Sort.by("id").descending());


		return iMovimientos.findAll(pageN);
	}
	
	
	public Movimientos guardar(Movimientos movimiento) {
		
		
		return iMovimientos.save(movimiento);
	}
	
	public Movimientos  getMovimientosXId(Long id) {
		
	
		Movimientos movimientos = null;

			try {
				Query query1 = em.createQuery("from Movimientos where id = " + id);
				movimientos = (Movimientos) query1.getSingleResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			
			return movimientos;
		}
	    
}
