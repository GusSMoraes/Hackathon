package hackathon.portos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackathon.portos.dto.PlanejamentoNavioDTO;
import hackathon.portos.dto.RespostaPlanejamentoDTO;
import hackathon.portos.service.PlanejamentoNavioService;

@RestController
@RequestMapping(path="/planejamentos")
public class PlanejamentoNavioController {
	
	@Autowired
	private PlanejamentoNavioService planejamentoNavioService;
	
	@PostMapping(path="/cadastrar")
	public @ResponseBody RespostaPlanejamentoDTO cadastrar(@RequestBody List<PlanejamentoNavioDTO> planejamentos) {
		planejamentoNavioService.cadastrarPlanejamentos(planejamentos);
		return planejamentoNavioService.realizarPlanejamento(planejamentos);
	}
	
	@GetMapping(path="/listar")
	public @ResponseBody List<PlanejamentoNavioDTO> listar(@RequestParam String data) {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return planejamentoNavioService.listarPlanejamentosPorData(s.parse(data));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
