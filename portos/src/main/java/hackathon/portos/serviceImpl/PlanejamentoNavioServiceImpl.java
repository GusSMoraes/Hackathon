package hackathon.portos.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hackathon.portos.dto.PlanejamentoNavioDTO;
import hackathon.portos.dto.RespostaPlanejamentoDTO;
import hackathon.portos.dto.TurnoDTO;
import hackathon.portos.model.NavioModel;
import hackathon.portos.model.PlanejamentoNavioModel;
import hackathon.portos.repository.NavioRepository;
import hackathon.portos.repository.PlanejamentoNavioRepository;
import hackathon.portos.service.PlanejamentoNavioService;

@Service
public class PlanejamentoNavioServiceImpl implements PlanejamentoNavioService {
	
	@Autowired
	private PlanejamentoNavioRepository planejamentoNavioRepository;
	
	@Autowired
	private NavioRepository navioRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void cadastrarPlanejamentos(List<PlanejamentoNavioDTO> planejamentos) {
		for (PlanejamentoNavioDTO planejamento : planejamentos) {
			PlanejamentoNavioModel planejamentoNavioModel = modelMapper.map(planejamento, PlanejamentoNavioModel.class);
			planejamentoNavioRepository.save(planejamentoNavioModel);
		}
	}

	@Override
	public List<PlanejamentoNavioDTO> listarPlanejamentosPorData(Date data) {
		List<PlanejamentoNavioModel> planejamentosModel = planejamentoNavioRepository.findByHorarioEntrada(data);
		List<PlanejamentoNavioDTO> ret = new LinkedList<PlanejamentoNavioDTO>();
		
		for (PlanejamentoNavioModel planejamentoModel : planejamentosModel) {
			ret.add(modelMapper.map(planejamentoModel, PlanejamentoNavioDTO.class));
		}
		
		return ret;
	}

	@Override
	public void alterarPlanejamentosPorData(Date data, PlanejamentoNavioDTO planejamentoDTO) {
		NavioModel navio = navioRepository.findByIdentificador(planejamentoDTO.getNavio().getIdentificador());
		
		PlanejamentoNavioModel planejamento = 
				planejamentoNavioRepository.findByHorarioEntradaAndNavio(data, navio);
		
		modelMapper.map(planejamentoDTO, planejamento);
		
		planejamentoNavioRepository.save(planejamento);
	}

	@Override
	public RespostaPlanejamentoDTO realizarPlanejamento(List<PlanejamentoNavioDTO> planejamentos) {
		
		List<PlanejamentoNavioDTO> entradaTurno1 = new LinkedList<PlanejamentoNavioDTO>();
		List<PlanejamentoNavioDTO> entradaTurno2 = new LinkedList<PlanejamentoNavioDTO>();
		List<PlanejamentoNavioDTO> entradaTurno3 = new LinkedList<PlanejamentoNavioDTO>();
		List<PlanejamentoNavioDTO> entradaTurno4 = new LinkedList<PlanejamentoNavioDTO>();
		
		List<PlanejamentoNavioDTO> participaTurno1 = new LinkedList<PlanejamentoNavioDTO>();
		List<PlanejamentoNavioDTO> participaTurno2 = new LinkedList<PlanejamentoNavioDTO>();
		List<PlanejamentoNavioDTO> participaTurno3 = new LinkedList<PlanejamentoNavioDTO>();
		List<PlanejamentoNavioDTO> participaTurno4 = new LinkedList<PlanejamentoNavioDTO>();

		for (PlanejamentoNavioDTO planejamento : planejamentos) {
			Date dataEntrada = planejamento.getHorarioEntrada();
			Date dataSaida = planejamento.getHorarioSaida();
			
			RespostaPlanejamentoDTO ret = 
					new RespostaPlanejamentoDTO();
			
			if (dataEntrada.getHours() - 2 <= 7) {
				entradaTurno1.add(planejamento);
				participaTurno1.add(planejamento);
				if(dataSaida.getHours() > 7) {
					participaTurno2.add(planejamento);
					
					
				}
			}
			else if (dataEntrada.getHours() - 2 <= 13) {
				entradaTurno2.add(planejamento);
			}
			else if (dataEntrada.getHours() - 2 <= 19) {
				entradaTurno3.add(planejamento);
			}
			else {
				entradaTurno4.add(planejamento);
			}
			
			
			if (dataSaida.getHours() > 1) {
				participaTurno1.add(planejamento);
			}
			if (dataSaida.getHours() > 7) {
				participaTurno2.add(planejamento);
			}
			if(dataSaida.getHours() > 13) {
				participaTurno3.add(planejamento);
			}
			if(dataSaida.getHours() > 19) {
				participaTurno4.add(planejamento);
			}
			
			int numeroTernos = 18, a = 2, b = 2, c = 2, d = 2, max_prob;
			numeroTernos -= 8;
	        if(numeroTernos > 4){
	            a += 4;
	            numeroTernos -= 4;
	            if(numeroTernos > 4){
	                b += 4;
	                numeroTernos -= 4;    
	                if(numeroTernos > 4){
	                    c += 4;
	                    numeroTernos -= 4;
	                    d += numeroTernos;
	                }else{
	                    c += numeroTernos;
	                }
	            }else{
	                b += numeroTernos;
	            }
	        }else{
	            a += numeroTernos;
	        }
	        max_prob = a * b * c * d;
			
	        
	        ArrayList<ArrayList<Integer> > aList =  
	                  new ArrayList<ArrayList<Integer> >();  
	        
	        Random gerador = new Random();
	        
	        while (aList.size() == max_prob) {
		        ArrayList<Integer> comb = new ArrayList<Integer>();
		        comb.add((gerador.nextInt() + 2) % 7);
		        comb.add((gerador.nextInt() + 2) % 7);
		        comb.add((gerador.nextInt() + 2) % 7);
		        comb.add((gerador.nextInt() + 2) % 7);
		        if (comb.get(0) + comb.get(1) + comb.get(2) + comb.get(3) == numeroTernos) {
		        	if (!aList.contains(comb)) {
		        		aList.add(comb);
		        		class Sort implements Comparator<PlanejamentoNavioDTO> 
		        		{ 
		        		    // Used for sorting in ascending order of 
		        		    // roll number 
		        		    public int compare(PlanejamentoNavioDTO a, PlanejamentoNavioDTO b) 
		        		    { 
		        		        return b.getQuantidadeOperacoes() - a.getQuantidadeOperacoes();
		        		    } 
		        		}
		        		participaTurno1.sort(new Sort());
		        		int min = 1 + (participaTurno1.get(0).getProdutividade().intValue() / 34);
		        		ret.getTurno1().add(new TurnoDTO());
		        		
		        		if (participaTurno1.size() == 1) {
		        			ret.getTurno1().add(new TurnoDTO(participaTurno1.get(0).getNavio().getIdentificador() ,comb.get(0)));
		        		} else if (participaTurno1.size() == 2) {
		        			if()
		        		}
		        	}
		        }
	        }
			
		}
		
		return ;
	}

}
