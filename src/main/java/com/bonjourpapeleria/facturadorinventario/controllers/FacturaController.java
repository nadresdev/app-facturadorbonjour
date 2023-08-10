package com.bonjourpapeleria.facturadorinventario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bonjourpapeleria.facturadorinventario.entity.Factura;
import com.bonjourpapeleria.facturadorinventario.entity.FacturaDetalle;
import com.bonjourpapeleria.facturadorinventario.entity.LineaFactura;
import com.bonjourpapeleria.facturadorinventario.entity.Producto;
import com.bonjourpapeleria.facturadorinventario.service.FacturaService;
import com.bonjourpapeleria.facturadorinventario.service.ProductoService;
import com.bonjourpapeleria.facturadorinventario.util.paginator.PageRender;

@Controller
@SessionAttributes("factura")
@RequestMapping("/factura")
public class FacturaController {

	
	
	@Autowired
private FacturaService facturaService;
	
	@Autowired
	private ProductoService productoService;
	

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
		for (int i = 1; i < lineasFactura.length; i++) {
		
			 Producto  producto = productoService.getProductoXId(Long.valueOf(lineasFactura[i]));
			 LineaFactura lineaFactura = new LineaFactura();
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
	@RequestMapping(value = "/graficas")
    public String listarFacturas(Model model) {

       
        model.addAttribute("titulo", "graficas");

     

       


        return "estadisticas";

    }
	

}
