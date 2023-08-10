package com.bonjourpapeleria.facturadorinventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bonjourpapeleria.facturadorinventario.entity.Factura;
import com.bonjourpapeleria.facturadorinventario.entity.Producto;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Long> {

	
}
