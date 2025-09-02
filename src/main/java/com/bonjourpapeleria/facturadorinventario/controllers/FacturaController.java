package com.bonjourpapeleria.facturadorinventario.controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.naming.factory.OpenEjbFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bonjourpapeleria.facturadorinventario.entity.Factura;
import com.bonjourpapeleria.facturadorinventario.entity.FacturaDetalle;
import com.bonjourpapeleria.facturadorinventario.entity.LineaFactura;
import com.bonjourpapeleria.facturadorinventario.entity.Movimientos;
import com.bonjourpapeleria.facturadorinventario.entity.Producto;
import com.bonjourpapeleria.facturadorinventario.entity.ProductoDisponibilidad;
import com.bonjourpapeleria.facturadorinventario.service.FacturaService;
import com.bonjourpapeleria.facturadorinventario.service.ProductoDisponibilidadService;
import com.bonjourpapeleria.facturadorinventario.service.ProductoService;
import com.bonjourpapeleria.facturadorinventario.util.paginator.PageRender;
import com.github.cliftonlabs.json_simple.JsonObject;



import ch.qos.logback.classic.Logger;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("factura")
@RequestMapping("/factura")
public class FacturaController {

	
	
	@Autowired
private FacturaService facturaService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ProductoDisponibilidadService disponibilidadService;
	

	@GetMapping("/facturar")
	public String nuevoProducto(Factura factura, Model model, RedirectAttributes flash) {
        
		model.addAttribute("titulo", "Nueva Factura");
		model.addAttribute("factura",factura);
		

		return "factura/facturar";

	}
	
	  @RequestMapping(value = "/listafacturas", method = RequestMethod.GET)
	    public String listarFacturas(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

	        Pageable pageRequest = PageRequest.of(page, 8);//Items por página

	        Page<Factura> facturasRecientes = facturaService.getFacturasRecientes(pageRequest);
	        PageRender<Factura> pageRender = new PageRender<Factura>("/factura/listafacturas", facturasRecientes);
	        model.addAttribute("titulo", "Lista de facturas");

	        model.addAttribute("facturasRecientes", facturasRecientes);

	        model.addAttribute("page", pageRender);


	        return "factura/listafacturas";

	    }
	

	@PostMapping("/guardarfactura")
	public String guardarFactura(Factura factura, 
			@RequestParam (name = "linea_id[]", required = false) String[] lineasFactura,
			@RequestParam (name = "cantidad[]", required = false) Long[] cantidadProducto
			
			
			,RedirectAttributes flash, SessionStatus status) {
		
		String tipoFactura =factura.getBorrador()?"BORRADOR":"RECAUDADO";
		FacturaDetalle facturaDetalle = new FacturaDetalle();
		factura.setTotalFactura(0L);
		facturaService.guardar(factura);
		Long totalFactura = 0L;
		Long totalCantidad = 0L;
		for (int i = 1; i < lineasFactura.length; i++) {
		
			 Producto  producto = productoService.getProductoXId(Long.valueOf(lineasFactura[i]));
			 LineaFactura lineaFactura = new LineaFactura();
			 ProductoDisponibilidad pd = producto.getProductoDisponibilidad();
			 //lineaFactura.setvalorImporte(Double.valueOf(totalImporte[i]));
			 lineaFactura.setId_categoria(producto.getCategoria().getId_categoria());
			 lineaFactura.setCantidad(cantidadProducto[i]);
			 lineaFactura.setProducto(producto);
			 lineaFactura.setEstado(tipoFactura);
			 lineaFactura.setObservacion(factura.getObservacion());
			 lineaFactura.setvalorImporte(lineaFactura.calcularTotalProducto());
			 factura.addLineaFactura(lineaFactura);
			 lineaFactura.setFactura(factura);
			 totalFactura+=  lineaFactura.calcularTotalProducto();
			pd.setStockProducto(pd.getStockProducto() - cantidadProducto[i]<=0?0:pd.getStockProducto() - cantidadProducto[i]);
			disponibilidadService.guardar(pd);
			
		}
		
		/*for (int i = 0; i < factura.getLineasFactura().size(); i++) {
			factura.getLineasFactura().get(i).setFactura(factura);
			
		}*/
	
		facturaDetalle.setFactura(factura);
		facturaDetalle.setFechaElaboracion(factura.getFecha_elaboracion());
		facturaDetalle.setValor(totalFactura);
		factura.setFacturaDetalle(facturaDetalle);
		factura.setTotalFactura(totalFactura);
		factura.setEstado(tipoFactura);
		facturaService.guardar(factura);
		 status.setComplete();
		flash.addFlashAttribute("success", "Factura guardada correctamente");
		return "redirect:/factura/listafacturas";
	}
	
	  
	  @GetMapping("/eliminar/{idFactura}")
	    public String eliminarXId(@PathVariable(name = "idFactura", required = false) Long idFactura,

	                              @Valid Factura factura, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {

		  
		 facturaService.borrarFacturaXId(idFactura);
	       
	                flash.addFlashAttribute("warning","Factura borrada");
	       


	status.setComplete();

	return "redirect:/factura/listafacturas";

	    }

	  
	  @GetMapping( value = "/getDetallesFactura/{idFactura}", produces = {"application/json"})
        public @ResponseBody List<JsonObject> getDetallesFacturaXidFactura(@PathVariable Long idFactura, 
	    		 Model model) {
		  
		  
		 
		 List<JsonObject> DetallesLineasJson = new ArrayList<>();
		 List<LineaFactura> lf =  facturaService.ListaProductosXiDFactura(idFactura).get(0).getLineasFactura();
	     
	    	for (int i = 0; i<lf.size();i++) {
	    		JsonObject js = new JsonObject();
	    		
	    		js.put("id", lf.get(i).getId_det_factura());
	    		js.put("Nombre", lf.get(i).getProducto().getNombre());
	    		js.put("Cantidad", lf.get(i).getCantidad());
	    		js.put("Valor_importe", lf.get(i).getvalorImporte());
	    		js.put("Valor_factura", lf.get(i).getFactura().getTotalFactura());
	    		
	    		
	    		DetallesLineasJson.add(js);
	    		
	    		
	    		
			}
	    	

	              return  DetallesLineasJson;   


	    }

	
	

}
