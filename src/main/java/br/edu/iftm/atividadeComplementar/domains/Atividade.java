package br.edu.iftm.atividadeComplementar.domains;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "TB_ATIVIDADE")
public class Atividade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(name = "ATV_NOME", nullable = false)
	@NotNull(message="Campo Nome precisa ser preenchido")
	@Size(min=3, message="Nome precisa ter pelo menos 3 caracteres")
	private String nome;
	
	@Column(name = "ATV_PERCENTUALCARGAHORARIA")
	@NotNull(message="Campo percentualCargaHoraria precisa ser preenchido")
	@Size(min=1, message="percentualCargaHoraria precisa ter pelo menos 1 caracteres")	
	private Integer percentualCargaHoraria;
	
	@Column(name = "ATV_MAXIMOATIVIDADESSEMESTRE")
	@NotNull(message="Campo maximoAtividadesSemestre precisa ser preenchido")
	@Size(min=1, message="maximoAtividadesSemestre precisa ter pelo menos 1 caracteres")	
	private Integer maximoAtividadesSemestre;
	
	@Column(name = "ATV_PERCENTUALPORATIVIDADE")
	@NotNull(message="Campo percentualPorAtividade precisa ser preenchido")
	@Size(min=1, message="percentualPorAtividade precisa ter pelo menos 1 caracteres")	
	private Integer percentualPorAtividade;

	public Atividade(Integer codigo, String nome, Integer percentualCargaHoraria, Integer maximoAtividadesSemestre,
			Integer percentualPorAtividade) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.percentualCargaHoraria = percentualCargaHoraria;
		this.maximoAtividadesSemestre = maximoAtividadesSemestre;
		this.percentualPorAtividade = percentualPorAtividade;
	}

	public Atividade() {
		super();
	}
	
	public Integer getValorLimiteHorasAtividade(Integer totalHorasComplementares) {
		return totalHorasComplementares * percentualPorAtividade / 100;
	}
	
	public Integer getHorasAproveitadasPorAtividade(Integer totalhorasComplementares) {
		return getValorLimiteHorasAtividade(totalhorasComplementares) * percentualCargaHoraria / 100; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPercentualCargaHoraria() {
		return percentualCargaHoraria;
	}

	public void setPercentualCargaHoraria(Integer percentualCargaHoraria) {
		this.percentualCargaHoraria = percentualCargaHoraria;
	}

	public Integer getMaximoAtividadesSemestre() {
		return maximoAtividadesSemestre;
	}

	public void setMaximoAtividadesSemestre(Integer maximoAtividadesSemestre) {
		this.maximoAtividadesSemestre = maximoAtividadesSemestre;
	}

	public Integer getPercentualPorAtividade() {
		return percentualPorAtividade;
	}

	public void setPercentualPorAtividade(Integer percentualPorAtividade) {
		this.percentualPorAtividade = percentualPorAtividade;
	}

}
