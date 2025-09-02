package com.bonjourpapeleria.facturadorinventario.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bonjourpapeleria.facturadorinventario.entity.Estadisticas;
import com.bonjourpapeleria.facturadorinventario.entity.Movimientos;
import com.bonjourpapeleria.facturadorinventario.service.EstadisticasService;
import com.bonjourpapeleria.facturadorinventario.service.MovimientosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bonjourpapeleria.facturadorinventario.entity.Producto;

@Controller
@RequestMapping("/estadisticas")
public class EstadisticasController {
	
	@Autowired
	private MovimientosService movimientosService;
	@Autowired
    private EstadisticasService estadisticasService;
	
	  @GetMapping(value = "/getestadisticas/{term}", produces = {"application/json"})
	    public @ResponseBody List<Estadisticas> cargarEstadisticas(@PathVariable String term) {


		 




		  return estadisticasService.getEstadisticasTodo(term);

	    }

	  @GetMapping(value = "/getestadisticasPerDay/{term}", produces = {"application/json"})
	  public @ResponseBody List<Optional> cargaEstadisticasPerDay(@PathVariable String term){
		  List<Optional> est = estadisticasService.getEstadisticasTodoMesXDia(term);
		  
		 
		  return est;
		  
	  }
	  
	  
	  
	  
	@RequestMapping(value = "/graficas")
	public String listarFacturas(Model model) {


		Estadisticas est = estadisticasService.getEstadisticasTodo("2023-08").get(0);
		model.addAttribute("titulo", "Estadísticas");

        model.addAttribute("ventas",est);




		return "statisticsB";

	}


}
