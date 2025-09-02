package com.bonjourpapeleria.facturadorinventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonjourpapeleria.facturadorinventario.entity.LineaFactura;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface ILineaFacturaRepository extends JpaRepository<LineaFactura, Long> {

	
	@Query(value = "select IFNULL(count(id_producto),0) from puntodeventa.detalles_factura where id_producto =:id", nativeQuery = true)
    Long ListaLineasProducto(Long id) ;
}
