package com.bonjourpapeleria.facturadorinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonjourpapeleria.facturadorinventario.entity.FacturaDetalle;

@Repository
public interface IFacturaDetalleRepository extends JpaRepository<FacturaDetalle, Long> {

}
