package com.bonjourpapeleria.facturadorinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonjourpapeleria.facturadorinventario.entity.LineaFactura;

@Repository
public interface ILineaFacturaRepository extends JpaRepository<LineaFactura, Long> {

}
