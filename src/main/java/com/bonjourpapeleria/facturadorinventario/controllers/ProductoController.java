package com.bonjourpapeleria.facturadorinventario.controllers;

import java.util.List;
import java.util.Optional;

import com.bonjourpapeleria.facturadorinventario.entity.ProductoDisponibilidad;
import com.bonjourpapeleria.facturadorinventario.service.ProductoDisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bonjourpapeleria.facturadorinventario.entity.Categoria;
import com.bonjourpapeleria.facturadorinventario.entity.ProductoDisponibilidad;
import com.bonjourpapeleria.facturadorinventario.entity.LineaFactura;
import com.bonjourpapeleria.facturadorinventario.entity.Producto;
import com.bonjourpapeleria.facturadorinventario.repository.ICategoriaRepository;
import com.bonjourpapeleria.facturadorinventario.service.CategoriaService;
import com.bonjourpapeleria.facturadorinventario.service.ProductoService;
import com.bonjourpapeleria.facturadorinventario.util.paginator.PageRender;

import jakarta.validation.Valid;

@Controller

@SessionAttributes("producto")
@RequestMapping("/formularios")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoDisponibilidadService productoDisponibilidadService;


    @GetMapping("/formproducto")
    public String nuevoProducto(Producto producto, Model model, RedirectAttributes flash, SessionStatus status) {

        model.addAttribute("titulo", "Nuevo producto");
        model.addAttribute("producto", producto);
        model.addAttribute("categoria", categoriaService.getCategorias()); 

        
           
        
        return "/formproducto";

    }

    @PostMapping("/guardarproducto")
    public String recibeProducto(@Valid Producto producto, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
    	
        ProductoDisponibilidad productoDisponibilidad = producto.getProductoDisponibilidad();
        producto.setCategoria(categoriaService.getCategoriaXId(producto.getCategoriaId()));
        productoDisponibilidad.setCategoria(categoriaService.getCategoriaXId(producto.getCategoriaId()));
        producto.precioBeneficio();


        productoDisponibilidadService.guardar(productoDisponibilidad);
        productoService.guardar(producto);
        productoDisponibilidad.setProducto(producto);
        productoDisponibilidadService.guardar(productoDisponibilidad);
       
        status.setComplete();
        flash.addFlashAttribute("success", "Producto guardado correctamente");
        return "redirect:/formularios/listaProductos";
        //return "redirect:/formularios/guardado";

    }

    @GetMapping("/guardado")
    public String guardado(@SessionAttribute(name = "producto", required = false) Producto producto, Model model,
                           SessionStatus status) {

        if (producto == null) {
        	// status.setComplete();
            return "redirect:/formularios/listaProductos";
            
        }



        model.addAttribute("titulo", "Producto guardado");

        //model.addAttribute("productosrecientes", productoService.getProductosRecientesQuery());
        status.setComplete();
        return "listaProductos";

    }

    @RequestMapping(value = "/listaProductos", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 8);//Items por página

        Page<Producto> productosRecientes = productoService.getProductosRecientes(pageRequest);
        PageRender<Producto> pageRender = new PageRender<Producto>("/formularios/listaProductos", productosRecientes);
        model.addAttribute("titulo", "Lista de productos");

        model.addAttribute("productosrecientes", productosRecientes);

        model.addAttribute("page", pageRender);


        return "productos";

    }

    @GetMapping("/formproducto/{idProducto}")
    public String recibeProducto(@PathVariable(name = "idProducto", required = false) Long idProducto,
                                 @Valid Producto producto, BindingResult result, Model model, SessionStatus status) {

        model.addAttribute("producto", productoService.getProductoXId(idProducto));
        model.addAttribute("categoria", categoriaService.getCategorias());
        model.addAttribute("titulo", "Editar Producto");
      
        return "formproducto";

    }

    @GetMapping("/eliminar/{idProducto}")
    public String eliminarXId(@PathVariable(name = "idProducto", required = false) Long idProducto,

                              @Valid Producto producto, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {

        if( productoService.getProductoXId(idProducto).getProductoDisponibilidad().getStockProducto().equals(0L)){


                productoService.borrarProductoXId(idProducto);
                flash.addFlashAttribute("warning","Producto borrado");
                
        }else {
        	flash.addFlashAttribute("error","No es posible eliminar un producto que existe en stock");
        	
        }


status.setComplete();

        return "redirect:/formularios/listaProductos";

    }

    @GetMapping(value = "/cargarproductos/{term}", produces = {"application/json"})
    public @ResponseBody List<Producto> cargarProductosXNombre(@PathVariable String term) {


        return productoService.getProductosXnombre("%" + term + "%");


    }


}
