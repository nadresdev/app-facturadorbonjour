package com.bonjourpapeleria.facturadorinventario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bonjourpapeleria.facturadorinventario.entity.Categoria;
import com.bonjourpapeleria.facturadorinventario.entity.Producto;
import com.bonjourpapeleria.facturadorinventario.repository.ICategoriaRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class CategoriaService {

	@Autowired
	private EntityManager em;
	@Autowired
	private ICategoriaRepository iCategoriaRepository;
	
	public Categoria guardar(Categoria categoria) {
		
		
		return iCategoriaRepository.save(categoria);
	}
	
	public Categoria  getCategoriaXId(Long id) {
		
	
		Categoria categoria = null;

			try {
				Query query1 = em.createQuery("from Categoria  where id_categoria = " + id);
				 categoria = (Categoria) query1.getSingleResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			
			return categoria;
		}



		
	public List<Categoria> getCategorias(){
		return iCategoriaRepository.findAll();
		
		
	}
	
public void eliminarCategoria(Categoria categoria) {
		
		
		 iCategoriaRepository.delete(categoria);
		 
		 
	}
	

public Page<Categoria> getTodasCategorias(Pageable pageN) {
	int pagina = pageN.getPageNumber();
	pageN = PageRequest.of(pagina, pageN.getPageSize());


	return iCategoriaRepository.findAll(pageN);
}
    
	
	
	
	
}
