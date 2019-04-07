package hackathon.portos.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class RespostaPlanejamentoDTO {

	private List<TurnoDTO> turno1;
	private List<TurnoDTO> turno2;
	private List<TurnoDTO> turno3;
	private List<TurnoDTO> turno4;
	
	public RespostaPlanejamentoDTO() {
		turno1 = new LinkedList<TurnoDTO>();
		turno2 = new LinkedList<TurnoDTO>();
		turno3 = new LinkedList<TurnoDTO>();
		turno4 = new LinkedList<TurnoDTO>();
	}

}
