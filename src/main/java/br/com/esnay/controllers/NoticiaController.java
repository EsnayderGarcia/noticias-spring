
package br.com.esnay.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.esnay.models.Noticia;
import br.com.esnay.services.CategoriaService;
import br.com.esnay.services.NoticiaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/noticia")
public class NoticiaController {

	private NoticiaService noticiaService;

	private CategoriaService categoriaService;

	@GetMapping("/cadastrar")
	public ModelAndView viewNoticiaForm(Noticia noticia) {

		ModelAndView mv = new ModelAndView("noticia-form");
		mv.addObject("categorias", this.categoriaService.listaCategorias());
		
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView cadastrarNoticia(@Valid Noticia noticia, BindingResult result,
						RedirectAttributes attr) {
		
			if(result.hasErrors()) {
				ModelAndView mv = new ModelAndView("noticia-form");
				mv.addObject("categorias", this.categoriaService.listaCategorias());
				mv.addObject("noticia", noticia);
				
				return mv;
			}
		
			this.noticiaService.salvarNoticia(noticia);
			attr.addFlashAttribute("msgSuccess", "Notícia Salva com Sucesso!");
			
			return new ModelAndView("redirect:/noticia/cadastrar");
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editarNoticia(@PathVariable Long id) {
		
		ModelAndView mv = new ModelAndView("noticia-form");
		
		mv.addObject("categorias", this.categoriaService.listaCategorias());
		mv.addObject("noticia", this.noticiaService.buscarNoticiaPorId(id));
		mv.addObject("mensagem", "Editar Notícia");
		
		return mv;
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarNoticia(@PathVariable Long id) {
		this.noticiaService.deletarNoticiaPorId(id);
		return "redirect:/home";
	}
	
}
