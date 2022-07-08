package br.com.esnay.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.esnay.models.Categoria;
import br.com.esnay.repository.CategoriaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;
	
	public Categoria encontrarCategoriaPorId(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public void salvarCategoria(Categoria categoria) {
		categoriaRepository.save(categoria);
	}
	
	public List<Categoria> listaCategorias() {

		List<Categoria> categorias = categoriaRepository.findByOrderByTipo();
		return categorias;
	}
	
	public Categoria encotrarCategoriaPorTipo(String tipoCategoria) {
		return this.categoriaRepository.findByTipoIgnoreCase(tipoCategoria);
	}
	
}
