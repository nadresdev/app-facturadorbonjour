package com.bonjourpapeleria.facturadorinventario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjourpapeleria.facturadorinventario.entity.Categoria;
import com.bonjourpapeleria.facturadorinventario.repository.ICategoriaRepository;

import jakarta.persistence.Entity;

@Service
public class CategoriaService {

	@Autowired
	private ICategoriaRepository iCategoriaRepository;
	
	public Categoria guardar(Categoria categoria) {
		
		
		return iCategoriaRepository.save(categoria);
	}
	
	public Categoria getCategoriaXId(Long id) {
		
		
		return iCategoriaRepository.getById(id);
	}
	
	public List<Categoria> getCategorias(){
		return iCategoriaRepository.findAll();
		
		
	}
	
    
	
	
	
	
}
