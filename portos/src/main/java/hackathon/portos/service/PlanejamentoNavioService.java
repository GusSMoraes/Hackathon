package hackathon.portos.service;

import java.util.Date;
import java.util.List;

import hackathon.portos.dto.PlanejamentoNavioDTO;
import hackathon.portos.dto.RespostaPlanejamentoDTO;

public interface PlanejamentoNavioService {
	
	public void cadastrarPlanejamentos(List<PlanejamentoNavioDTO> planejamentos);
	
	public List<PlanejamentoNavioDTO> listarPlanejamentosPorData(Date data);
	
	public void alterarPlanejamentosPorData(Date data, PlanejamentoNavioDTO navio);
	
	public RespostaPlanejamentoDTO realizarPlanejamento(List<PlanejamentoNavioDTO> planejamentos);
	
}
