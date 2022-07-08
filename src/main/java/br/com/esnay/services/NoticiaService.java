package br.com.esnay.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.esnay.models.Noticia;
import br.com.esnay.repository.NoticiaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoticiaService {
	
	private NoticiaRepository noticiaRepository;
	
	public List<Noticia> listaNoticias() {
		return this.noticiaRepository.findByOrderByDataRegistradaDesc();
	}
	
	public void salvarNoticia(Noticia noticia) {
		
		Date dataAtual = Calendar.getInstance().getTime();
		
		if(noticia.getId() == null) {
			noticia.setDataRegistrada(dataAtual);
			this.noticiaRepository.save(noticia);
		}
		else {
			Noticia noticiaAtualizada = this.buscarNoticiaPorId(noticia.getId());
			
			noticiaAtualizada.setCategoria(noticia.getCategoria());
			noticiaAtualizada.setTitulo(noticia.getTitulo());
			noticiaAtualizada.setDescricao(noticia.getDescricao());
			noticiaAtualizada.setDataAtualizada(dataAtual);
			
			this.noticiaRepository.save(noticiaAtualizada);
		}
	}
	
	public List<Noticia> buscarNoticiaPorTituloOuCategoria(String tituloOuCategoria) {
		
		List<Noticia> noticias = noticiaRepository
				.noticiaPorTituloOuCategoria(tituloOuCategoria.trim().toUpperCase());

		return noticias;
	}
	
	public Noticia buscarNoticiaPorId(Long id) {
		return this.noticiaRepository.findById(id).orElse(null);
	}
	
	public void deletarNoticiaPorId(Long id) {
		this.noticiaRepository.deleteById(id);
	}
}





