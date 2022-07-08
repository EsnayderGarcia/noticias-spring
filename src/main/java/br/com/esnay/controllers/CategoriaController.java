package br.com.esnay.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.esnay.models.Categoria;
import br.com.esnay.services.CategoriaService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/categoria")
@AllArgsConstructor
public class CategoriaController {
	
	private CategoriaService categoriaService;
	
	@GetMapping("/cadastrar")
	public ModelAndView categoriaForm(Categoria categoria) {
		
		ModelAndView mv = new ModelAndView("categoria-form");
		mv.addObject("categorias", this.categoriaService.listaCategorias());
		
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarCategoria(@Valid Categoria categoria,
			BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("categoria-form");
			
			mv.addObject("categoria", categoria);
			mv.addObject("categorias", this.categoriaService.listaCategorias());
			
			return mv;
		}
		//Verifica se já existe uma categoria com o mesmo nome na base de dados.
		else if(this.categoriaService.
					encotrarCategoriaPorTipo(categoria.getTipo()) != null) {
			ModelAndView mv = new ModelAndView("categoria-form");
			
			mv.addObject("categoria", categoria);
			mv.addObject("categorias", this.categoriaService.listaCategorias());
			mv.addObject("msgCategoriaJaExiste", "Já existe uma categoria deste tipo cadastrada!");			
			
			return mv;
		}
		
		this.categoriaService.salvarCategoria(categoria);
		attr.addFlashAttribute("msgSuccess", "Categoria Salva com Sucesso!");
		
		return new ModelAndView("redirect:/categoria/cadastrar");
	}
	
}
