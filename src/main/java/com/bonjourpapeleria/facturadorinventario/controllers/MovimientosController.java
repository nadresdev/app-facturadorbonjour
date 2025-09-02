package com.bonjourpapeleria.facturadorinventario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bonjourpapeleria.facturadorinventario.entity.Categoria;
import com.bonjourpapeleria.facturadorinventario.entity.Movimientos;
import com.bonjourpapeleria.facturadorinventario.service.MovimientosService;
import com.bonjourpapeleria.facturadorinventario.util.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("movimientos")
@RequestMapping("/listas")
public class MovimientosController {
	
@Autowired
private MovimientosService movimientosService;
	  
	  @RequestMapping(value = "/listamovimientos", method = RequestMethod.GET)
	    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model, Movimientos movimientos,SessionStatus status) {
   
		  
		  status.setComplete();

		  Pageable pageRequest = PageRequest.of(page, 8);//Items por página

		  
	        Page<Movimientos> movimientosrecientes = movimientosService.getTodosMovimientos(pageRequest);
	        PageRender<Movimientos> pageRender = new PageRender<>("/listas/listamovimientos", movimientosrecientes);
	        model.addAttribute("titulo", "Nuevo movimiento");

	        model.addAttribute("movimientosrecientes", movimientosrecientes);

	        model.addAttribute("page", pageRender);
	        model.addAttribute("movimiento",movimientos);
	       

	        return "movimientoslista";

	    }
	  
	  
	  @PostMapping("/guardarmovimiento")
	    public String recibeCategoriaGuardar(Movimientos movimientos, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
	    	
		Movimientos m = movimientos;
		
		
	        movimientosService.guardar(movimientos);
	       
	        status.setComplete();
	        flash.addFlashAttribute("success", "Movimiento Guardado");
	        return "redirect:/listas/listamovimientos";
	        //return "redirect:/formularios/guardado";

	    }
	  
	  @GetMapping("/frmmovimientoditar/{idMovimiento}")
	    public String editarCategoria(@PathVariable(name = "idMovimiento", required = false) Long idMovimiento,
	                                 @Valid Movimientos movimientos, BindingResult result, Model model, SessionStatus status) {

		  status.setComplete();
		  movimientos = movimientosService.getMovimientosXId(idMovimiento);
		 
		  Pageable pageRequest = PageRequest.of(0, 8);//Items por página

	        Page<Movimientos> movimientosrecientes = movimientosService.getTodosMovimientos(pageRequest);
	        PageRender<Movimientos> pageRender = new PageRender<>("/listas/listamovimientos", movimientosrecientes);
	        model.addAttribute("titulo", "Editar Movimiento");

	        model.addAttribute("movimientosrecientes", movimientosrecientes);

	        model.addAttribute("page", pageRender);
	        model.addAttribute("movimientos",movimientos);

	       
	        
	      
	        
	     
	       
	        return "movimientoslista";

	    }
	  
	  @GetMapping("/eliminarmovimiento/{idMovimiento}")
	    public String eliminarXId(@PathVariable(name = "idMovimiento", required = false) Long idMovimiento,

	                              @Valid Movimientos movimientos, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {

		  
		 movimientosService.borrarXId(idMovimiento);
	       
	                flash.addFlashAttribute("warning","Movimiento borrado");
	       


	status.setComplete();

	 return "redirect:/listas/listamovimientos";

	    }

}
