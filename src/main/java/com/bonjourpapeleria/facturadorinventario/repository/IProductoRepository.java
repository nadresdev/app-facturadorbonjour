package com.bonjourpapeleria.facturadorinventario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bonjourpapeleria.facturadorinventario.entity.Producto;




public interface IProductoRepository  extends JpaRepository<Producto, Long>{

	
	
	/*@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
	
	public List<Producto> findByNombreLikeIgnoreCase(String term);
	
	*/
	
	  @Query(value = "select p.nombre, df.cantidad,df.valor_importe, f.total_factura  from puntodeventa.facturas f\r\n"
	  		+ " inner join puntodeventa.detalles_factura df on df.id_factura = f.id_factura\r\n"
	  		+ " inner join puntodeventa.productos p on p.id_producto = df.id_producto\r\n"
	  		+ " inner join puntodeventa.producto_disponibilidad pd on pd.id_producto = p.id_producto\r\n"
	  		+ " where  f.id_factura =:idFactura " , nativeQuery = true) 
	    List<Optional> ListaProductosXiDFactura(Long idFactura) ;  

}
