package com.bonjourpapeleria.facturadorinventario.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjourpapeleria.facturadorinventario.entity.Estadisticas;
import com.bonjourpapeleria.facturadorinventario.entity.FacturaDetalle;
import com.bonjourpapeleria.facturadorinventario.entity.LineaFactura;
import com.bonjourpapeleria.facturadorinventario.repository.ILineaFacturaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Service
public class LineaFacturaService {

	@Autowired
	private ILineaFacturaRepository facturaRepository;
	
	@Autowired
	private EntityManager em;
	
	public LineaFactura guardar(LineaFactura lineaFactura) {
		return facturaRepository.save(lineaFactura);
		
		
	}
	
	public Long ListaLineasProducto(Long id) {
		
		return facturaRepository.ListaLineasProducto(id);
	}
	


	
		
}
