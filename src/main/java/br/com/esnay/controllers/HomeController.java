package br.com.esnay.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.esnay.models.Noticia;
import br.com.esnay.services.NoticiaService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private NoticiaService noticiaService;

	@GetMapping("/home")
	public ModelAndView viewHome() {
		
		List<Noticia> noticias = this.noticiaService.listaNoticias();
		
		ModelAndView mv = new ModelAndView("index");

		if(!noticias.isEmpty()) {
			mv.addObject("noticias", noticias);
		} else {
			mv.addObject("mensagem", "Não há notícias cadastradas");
		}

		return mv;
	}
	
	@PostMapping("/buscar")
	public ModelAndView buscarNoticia(@RequestParam("buscar") String tituloOuCategoria) {

		ModelAndView mv = new ModelAndView("index");
		
		if(!tituloOuCategoria.isBlank()) {
			List<Noticia> noticias = noticiaService
										 .buscarNoticiaPorTituloOuCategoria(tituloOuCategoria);
			
			if(!noticias.isEmpty()) {
				mv.addObject("noticias", noticias);
			} 
			else {
				mv.addObject("mensagem", "Ops! Não Encontramos resultado(s) para: "+tituloOuCategoria);
			}
			
			return mv;
		}
		
		mv.addObject("mensagem", "Informe o título ou a categoria da notícia no campo de busca");
		return mv;
	}
}
