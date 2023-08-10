package com.bonjourpapeleria.facturadorinventario.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjourpapeleria.facturadorinventario.entity.LineaFactura;
import com.bonjourpapeleria.facturadorinventario.repository.ILineaFacturaRepository;

@Service
public class LineaFacturaService {

	@Autowired
	private ILineaFacturaRepository facturaRepository;
	
	public LineaFactura guardar(LineaFactura lineaFactura) {
		return facturaRepository.save(lineaFactura);
		
		
	}
	
		
}
