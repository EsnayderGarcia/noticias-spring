package br.com.esnay.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noticia implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo título é obrigatório!")
	@NotBlank(message = "O campo título é obrigatório!")
	@Size(min = 3, max = 30, message = "O campo título deve ter no mínimo {min} e no máximo {max} caracteres.")
	private String titulo;
	
	@NotBlank(message = "O campo descrição deve ser informado.")
	@Size(min = 5, max = 255, message = "O campo descrição deve ter no mínimo {min} e no máximo {max} caracteres.")
	private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "data_registrada")
	private Date dataRegistrada;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "data_atualizada")
	private Date dataAtualizada;
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private Categoria categoria;

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", dataRegistrada="
				+ dataRegistrada + ", dataAtualizada=" + dataAtualizada + ", categoria=" + categoria + "]";
	}
}
