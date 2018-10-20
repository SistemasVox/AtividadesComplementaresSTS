package br.edu.iftm.atividadeComplementar.domains;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class LancamentoAtividade {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(name = "Lanc_quantidadeHoras")
	@NotNull(message="Campo quantidadeHoras precisa ser preenchido")
	@Size(min=3, message="quantidadeHoras precisa ter pelo menos 1 caracteres")	
	private Integer quantidadeHoras;
	
	@Column(name = "Lanc_dataInicio")
	@NotNull(message="Campo dataInicio precisa ser preenchido")
	@Size(min=3, message="dataInicio precisa ter pelo menos 10 caracteres")	
	private Date dataInicio;
	
	@Column(name = "Lanc_dataFim")
	@NotNull(message="Campo dataFim precisa ser preenchido")
	@Size(min=3, message="dataFim precisa ter pelo menos 10 caracteres")	
	private Date dataFim;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Atividade atividade;

	public LancamentoAtividade(Integer codigo, Integer quantidadeHoras, Date dataInicio, Date dataFim, Aluno aluno,
			Atividade atividade) {
		super();
		this.codigo = codigo;
		this.quantidadeHoras = quantidadeHoras;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.aluno = aluno;
		this.atividade = atividade;
	}
	
	public String getSemestreAtividade() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataFim);
		int ano = calendar.get(Calendar.YEAR);
		int mes = calendar.get(Calendar.MONTH);
		if (mes > 5) {
			return ano+"-2";
		} else {
			return ano+"-1";
		}
	}
	
	public Integer getHorasAproveitadas() {
		if (this.quantidadeHoras > this.atividade.getValorLimiteHorasAtividade(quantidadeHoras)) {
			return this.atividade.getValorLimiteHorasAtividade(quantidadeHoras);
		} else {
			return this.quantidadeHoras;
		}		
	}

	public LancamentoAtividade() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras(Integer quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	

}
