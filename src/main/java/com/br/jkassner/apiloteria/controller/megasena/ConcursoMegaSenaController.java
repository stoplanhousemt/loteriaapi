package com.br.jkassner.apiloteria.controller.megasena;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.sentry.SentryClient;
import com.br.jkassner.apiloteria.model.ConcursoMegaSena;
import com.br.jkassner.apiloteria.model.ICounterPosicao;
import com.br.jkassner.apiloteria.repository.megasena.ConcursoMegaSenaRepository;
import com.br.jkassner.apiloteria.service.concurso.ConcursoService;
import com.br.jkassner.apiloteria.service.ParserContentFileService;

@RestController
@RequestMapping("/megasena")
public class ConcursoMegaSenaController {

	@Autowired
	@Qualifier("concursoMegaSenaServiceImpl")
	ConcursoService<ConcursoMegaSena> concursoService;

	@Autowired
	ConcursoMegaSenaRepository concursoMegaSenaRepository;

	@Autowired
	@Qualifier("parseContentFileMegaSenaServiceImpl")
	ParserContentFileService<ConcursoMegaSena> parseContentFileServiceImpl;

	@Autowired
	SentryClient sentryClient;

	@GetMapping("/{idConcurso}")
	public ResponseEntity<?> getConcurso(@PathVariable("idConcurso") Long idConcurso) throws Exception {
		ConcursoMegaSena concursoMegaSena = concursoService.findByIdConcurso(idConcurso);

		return ResponseEntity.ok(concursoMegaSena);
	}

	@GetMapping("/find-concursos")
	public ResponseEntity<?> findConcursos(@RequestParam(value = "dezenasUsuario") List<Integer> dezenasUsuario) {

		sentryClient.sendMessage("Iniciando busca por concursos premiados");

		Map<String, List<ConcursoMegaSena>> concursosByDezenas = concursoService.findConcursosByDezenas(true, true,
				true, dezenasUsuario);

		if (concursosByDezenas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(concursosByDezenas);
	}

	@GetMapping("/populaResultados")
	public ResponseEntity<?> populaResultados() {

		new Thread(() -> {
			try {
				parseContentFileServiceImpl.populaResultados();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

		return ResponseEntity.noContent().build();
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> buscaUltimoConcurso() {

		ConcursoMegaSena concurso = concursoService.getUltimoConcurso();
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);

		return ResponseEntity.ok().cacheControl(cacheControl).body(concurso);
	}

	@GetMapping("/counter-posicoes")
	public ResponseEntity<?> getCounterPosicao(@RequestParam("page") int page) {

		Map<Long, List<ICounterPosicao>> counterPosicoes = concursoService.getCounterPosicoes(page);
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);
		return ResponseEntity.ok().cacheControl(cacheControl).body(counterPosicoes);
	}
}