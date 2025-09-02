package com.bonjourpapeleria.facturadorinventario.repository;

import com.bonjourpapeleria.facturadorinventario.entity.Categoria;

import com.bonjourpapeleria.facturadorinventario.entity.Movimientos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientos extends JpaRepository<Movimientos,Long> {




}
