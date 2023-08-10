package com.bonjourpapeleria.facturadorinventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjourpapeleria.facturadorinventario.entity.FacturaDetalle;
import com.bonjourpapeleria.facturadorinventario.entity.Factura;
import com.bonjourpapeleria.facturadorinventario.repository.IFacturaDetalleRepository;
import com.bonjourpapeleria.facturadorinventario.repository.IFacturaRepository;

@Service
public class FacturaDetalleService {

	@Autowired
	private IFacturaDetalleRepository facturaDetalleRepository;
	
	public FacturaDetalle guardar(FacturaDetalle factura) {
		
		
		return facturaDetalleRepository.save(factura);
	}
	
    public List<FacturaDetalle> listar() {
		
		
		return facturaDetalleRepository.findAll();
	}
	
    public Optional<FacturaDetalle> listarPorId(Long id) {
		
		
		return facturaDetalleRepository.findById(id);
	}
	
}
