package hackathon.portos.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PlanejamentoNavioDTO {
	
	private NavioDTO navio;
	private Date horarioEntrada;
	private Date horarioSaida;
	private Integer produtividade;
	private Boolean isAtrasado;
	private Integer quantidadeOperacoes;
	private Integer tempoOperacao;
	
}
