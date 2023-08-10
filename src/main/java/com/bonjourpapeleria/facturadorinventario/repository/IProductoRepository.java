package com.bonjourpapeleria.facturadorinventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bonjourpapeleria.facturadorinventario.entity.Producto;




public interface IProductoRepository  extends JpaRepository<Producto, Long>{

	
	
	/*@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
	
	public List<Producto> findByNombreLikeIgnoreCase(String term);
	*/

}
