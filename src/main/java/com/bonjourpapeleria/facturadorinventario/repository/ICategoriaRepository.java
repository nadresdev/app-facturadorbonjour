package com.bonjourpapeleria.facturadorinventario.repository;

import com.bonjourpapeleria.facturadorinventario.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria,Long> {


}
