package hackathon.portos.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Table(name="planejamento")
@Entity
@Data
public class PlanejamentoNavioModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="navio_id")
	private NavioModel navio;
	
	@Column(name="vl_horario_entrada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioEntrada;
	
	@Column(name="qtd_operacao_hora_minimo")
	private Integer produtividade;
	
	@Column(name="vl_horario_saida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioSaida;
	
	@Column(name="qtd_operacoes")
	private Integer quantidadeOperacoes;
	
	@Column(name="ic_atrasado")
	private Boolean isAtrasado;
	
	public PlanejamentoNavioModel(Date horarioEntrada, Integer produtividade, Date horarioSaida, Integer quantidadeOperacoes, Boolean isAtrasado) {
		this.horarioEntrada = horarioEntrada;
		this.produtividade = produtividade;
		this.horarioSaida = horarioSaida;
		this.quantidadeOperacoes = quantidadeOperacoes;
		this.isAtrasado = isAtrasado;
	}
	
	public PlanejamentoNavioModel() {}
	
}
