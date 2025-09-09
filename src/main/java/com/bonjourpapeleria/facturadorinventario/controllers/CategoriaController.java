package com.bonjourpapeleria.facturadorinventario.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bonjourpapeleria.facturadorinventario.entity.Categoria;
import com.bonjourpapeleria.facturadorinventario.service.CategoriaService;
import com.bonjourpapeleria.facturadorinventario.service.ProductoDisponibilidadService;
import com.bonjourpapeleria.facturadorinventario.util.paginator.PageRender;

import jakarta.validation.Valid;

@Controller

@SessionAttributes("categoria")
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ProductoDisponibilidadService disponibilidadService;

	@RequestMapping(value = "/listaCategorias", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model, Categoria categoria,
			SessionStatus status) {

		status.setComplete();

		Pageable pageRequest = PageRequest.of(page, 8);// Items por página

		Page<Categoria> categoriasTodas = categoriaService.getTodasCategorias(pageRequest);
		PageRender<Categoria> pageRender = new PageRender<>("/categorias/listaCategorias", categoriasTodas);
		model.addAttribute("titulo", "Nueva Categoria");

		model.addAttribute("listacategorias", categoriasTodas);

		model.addAttribute("page", pageRender);
		model.addAttribute("categoria", categoria);

		return "categorias";

	}

	@GetMapping("/formcategoriaeditar/{idCategoria}")
	public String editarCategoria(@PathVariable(name = "idCategoria", required = false) Long idCategoria,
			@Valid Categoria categoria, BindingResult result, Model model, SessionStatus status) {

		categoria = categoriaService.getCategoriaXId(idCategoria);
		Pageable pageRequest = PageRequest.of(0, 8);// Items por página

		Page<Categoria> categoriasTodas = categoriaService.getTodasCategorias(pageRequest);
		PageRender<Categoria> pageRender = new PageRender<>("/categorias/listaCategorias", categoriasTodas);
		model.addAttribute("titulo", "Editar Categoria");

		model.addAttribute("listacategorias", categoriasTodas);

		model.addAttribute("page", pageRender);
		model.addAttribute("categoria", categoria);

		model.addAttribute("titulo", "Editar Categoria");
		status.setComplete();
		return "categorias";

	}

	@PostMapping("/guardarcategoria")
	public String recibeCategoriaGuardar(@Valid Categoria categoria, BindingResult result, Model model,
			SessionStatus status, RedirectAttributes flash) {

		categoriaService.guardar(categoria);

		status.setComplete();
		flash.addFlashAttribute("success", "Categoria Guardada");
		return "redirect:/categorias/listaCategorias";
		// return "redirect:/formularios/guardado";

	}

	@GetMapping("/eliminarcategoria/{idCategoria}")
	public String eliminarXId(@PathVariable(name = "idCategoria", required = false) Long idCategoria,

			@Valid Categoria categoria, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {

		categoria = categoriaService.getCategoriaXId(idCategoria);
		if (disponibilidadService.getProductosPorCategoria(idCategoria).size() == 0L) {

			categoriaService.eliminarCategoria(categoria);
			flash.addFlashAttribute("warning", "Categoria borrada");

		} else {
			flash.addFlashAttribute("error",
					"No es posible eliminar una categoria que tiene productos, elimine primero los productos");

		}

		status.setComplete();

		return "redirect:/categorias/listaCategorias";

	}

	@GetMapping(value = "/getcategoriaeditar/{term}", produces = { "application/json" })
	public @ResponseBody Categoria cargarProductosXNombre(@PathVariable Long term) {
		Categoria c = categoriaService.getCategoriaXId(term);
		;
		return c;

	}

}
