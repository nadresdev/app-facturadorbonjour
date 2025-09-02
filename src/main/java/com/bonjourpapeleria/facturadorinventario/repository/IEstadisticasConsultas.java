package com.bonjourpapeleria.facturadorinventario.repository;

import com.bonjourpapeleria.facturadorinventario.entity.Estadisticas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;


@Repository
public interface IEstadisticasConsultas  extends JpaRepository<Estadisticas,Long> {
 
    @Query(value = "select 0 as id, sum(a.beneficio_total) as total_beneficio , sum(a.inventario_total)\n" +
            " as total_inventario, sum(a.ganancia) as total_profit, sum(a.cantidad_productos) as cantidad_ventas, IFNULL((select sum(mv.valor) from puntodeventa.movimientos mv where DATE_FORMAT(mv.fecha_elaboracion, '%Y-%m')=:sentencia \r\n"
            + " and mv.signo =\"-\"),0)as total_egreso, :sentencia as fecha_objetivo from (\n" +
            "select p.precio_beneficio * sum(df.cantidad) as beneficio_total,\n" +
            "p.precio_inventario * sum(df.cantidad) as inventario_total,\n" +
            "(p.precio_beneficio * sum(df.cantidad) )\n" +
            "- (p.precio_inventario * sum(df.cantidad))as ganancia, sum(df.cantidad)  as cantidad_productos\n" +
            " from puntodeventa.detalles_factura df\n" +
            "inner join puntodeventa.productos p on p.id_producto = df.id_producto\n" +
            "where  \n" +
            "DATE_FORMAT(df.fecha_elaboracion, '%Y-%m') =:sentencia \n" +
            "group by p.precio_beneficio, p.precio_inventario, p.precio_beneficio) a", nativeQuery = true)
    List<Estadisticas> ListaEstadisticas(String sentencia) ;
     
     
    @Query(value = "select 0 as id, IFNULL(sum(a.beneficio_total),0) as total_beneficio ,  IFNULL(sum(a.inventario_total),0)\r\n"
    		+ " as total_inventario,  IFNULL(sum(a.ganancia),0) as total_profit,  IFNULL(sum(cantidad_productos),0) as cantidad_ventas,IFNULL((select sum(mv.valor) from puntodeventa.movimientos mv where mv.fecha_elaboracion=a.fecha_elaboracion \r\n"
    		+ " and mv.signo =\"-\"),0)as total_egreso, DATE_FORMAT(a.fecha_elaboracion,'%M %d') as fecha_objetivo from (\r\n"
    		+ "select p.precio_beneficio * sum(df.cantidad) as beneficio_total,\r\n"
    		+ "p.precio_inventario * sum(df.cantidad) as inventario_total,\r\n"
    		+ "(p.precio_beneficio * sum(df.cantidad) )\r\n"
    		+ "- (p.precio_inventario * sum(df.cantidad))as ganancia, \r\n"
    		+ "0\r\n"
    		
    		+ ", sum(df.cantidad)  as cantidad_productos,df.fecha_elaboracion\r\n"
    		+ " from puntodeventa.detalles_factura df\r\n"
    		+ "inner join puntodeventa.productos p on p.id_producto = df.id_producto\r\n"
    		+ "group by p.precio_beneficio, p.precio_inventario, p.precio_beneficio,df.fecha_elaboracion) a\r\n"
    		+ "where DATE_FORMAT(a.fecha_elaboracion, '%Y-%m')=:sentenciab\r\n"
    		+ "group by a.fecha_elaboracion " , nativeQuery = true) 
    List<Optional> ListaEstadisticasXDia(String sentenciab) ;  
    
	

	
	
}

