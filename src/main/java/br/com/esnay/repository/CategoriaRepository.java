package br.com.esnay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.esnay.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	public List<Categoria> findByOrderByTipo();
	
	public Categoria findByTipoIgnoreCase(String nomeDaCategoria);
}
