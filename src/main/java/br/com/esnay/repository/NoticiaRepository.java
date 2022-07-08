package br.com.esnay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.esnay.models.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
	
	List<Noticia> findByOrderByDataRegistradaDesc();
 	
	@Query("select n from Noticia n "
			+ "where upper(n.titulo) like %?1% "
			+ "or upper(n.categoria.tipo) like %?1% order by dataRegistrada desc")
	List<Noticia> noticiaPorTituloOuCategoria(String tituloOuCategoria);
	
}
