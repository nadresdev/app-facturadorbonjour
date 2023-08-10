package com.bonjourpapeleria.facturadorinventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonjourpapeleria.facturadorinventario.entity.ProductoDisponibilidad;

@Repository
public interface IProductoDisponibilidad extends JpaRepository<ProductoDisponibilidad, Long> {

}
