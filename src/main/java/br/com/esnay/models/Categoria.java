
package br.com.esnay.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo categoria deve ser informado!")
	@NotNull(message = "O campo título é obrigatório!")
	@Size(min = 3, max = 20, message = "O campo categoria deve ter no mínimo {min} e no máximo {max} caracteres")
	private String tipo;

	@OneToMany(mappedBy = "categoria")
	private List<Noticia> noticias;

	public Categoria() {
						
	}
	
	public Categoria(String tipo) {
		this.tipo = tipo;
	}
	
	public Categoria(String tipo, List<Noticia> noticias) {
		this.tipo = tipo;
		this.noticias = noticias;
	}

	public Categoria(Long id, String tipo, List<Noticia> noticias) {
		this.id = id;
		this.tipo = tipo;
		this.noticias = noticias;
	}
}
