package hackathon.portos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackathon.portos.model.NavioModel;
import hackathon.portos.model.PlanejamentoNavioModel;

@Repository
public interface PlanejamentoNavioRepository extends JpaRepository<PlanejamentoNavioModel, Long> {

	public List<PlanejamentoNavioModel> findByHorarioEntrada(Date horarioEntrada);
	
	public PlanejamentoNavioModel findByHorarioEntradaAndNavio(Date horarioEntrada, NavioModel navio);
	
	public List<PlanejamentoNavioModel> findByHorarioSaidaGreaterThanEqual(Date dataAtual);
	
}
