package hackathon.portos.dto;

import lombok.Data;

@Data
public class TurnoDTO {
	
	String identificador;
	Integer ternos;
	
	public TurnoDTO(String identificador, Integer ternos) {
		this.identificador = identificador;
		this.ternos = ternos;
	}
	
}
