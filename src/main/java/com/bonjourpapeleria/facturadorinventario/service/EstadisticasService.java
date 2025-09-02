package com.bonjourpapeleria.facturadorinventario.service;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.bonjourpapeleria.facturadorinventario.entity.Estadisticas;
import com.bonjourpapeleria.facturadorinventario.repository.IEstadisticasConsultas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedNativeQuery;

@Service
public class EstadisticasService {


@Autowired
	IEstadisticasConsultas iEstadisticasConsultas;


public List<Estadisticas> getEstadisticasTodo(String sentencia){

	return iEstadisticasConsultas.ListaEstadisticas(sentencia);

}

public List<Optional> getEstadisticasTodoMesXDia(String sentencia){

	return iEstadisticasConsultas.ListaEstadisticasXDia(sentencia);

}


}
