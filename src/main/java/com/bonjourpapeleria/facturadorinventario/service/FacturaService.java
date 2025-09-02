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
import com.bonjourpapeleria.facturadorinventario.entity.Factura;
import com.bonjourpapeleria.facturadorinventario.entity.LineaFactura;
import com.bonjourpapeleria.facturadorinventario.entity.Producto;
import com.bonjourpapeleria.facturadorinventario.repository.ICategoriaRepository;
import com.bonjourpapeleria.facturadorinventario.repository.IFacturaRepository;
import com.bonjourpapeleria.facturadorinventario.repository.IProductoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class FacturaService {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private IFacturaRepository facturaRepository;
	
	@Autowired
	private ProductoService productoService;
	
	public Factura guardar(Factura factura) {
		
		
		return facturaRepository.save(factura);
	}
	
    public List<Factura> listar() {
		
		
		return (List<Factura>) facturaRepository.findAll();
	}
	
    public Optional<Factura> listarPorId(Long id) {
		
		
		return facturaRepository.findById(id);
	}
	
public Producto buscarProductoXId(Long id) {
	
	return productoService.getProductoXId(id);
}

public Page<Factura> getFacturasRecientes(Pageable pageN) {
	int pagina = pageN.getPageNumber();
	pageN = (Pageable) PageRequest.of(pagina, pageN.getPageSize()).withSort(Sort.by("idFactura").descending());


	return facturaRepository.findAll(pageN);
}

public void borrarFacturaXId(Long id) {
	
	facturaRepository.deleteById(id);
	
}

public List<Factura> ListaProductosXiDFactura(Long idFactura) {
	List<Factura> detallesFactura = new ArrayList<Factura>();

	try {
		Query query1 = em.createQuery("from Factura f inner join LineaFactura l  where f.idFactura =" + idFactura );
		detallesFactura = query1.getResultList();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	return detallesFactura;
}


}
