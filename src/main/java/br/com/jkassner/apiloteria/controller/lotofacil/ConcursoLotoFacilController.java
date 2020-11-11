package br.com.jkassner.apiloteria.controller.lotofacil;

import br.com.jkassner.apiloteria.controller.AbstractController;
import br.com.jkassner.apiloteria.model.ConcursoLotoFacil;
import br.com.jkassner.apiloteria.service.AbstractConcursoService;
import br.com.jkassner.apiloteria.service.ParserContentFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotofacil")
public class ConcursoLotoFacilController extends AbstractController<ConcursoLotoFacil> {

	@Autowired
	public ConcursoLotoFacilController(@Qualifier("concursoLotoFacilServiceImpl")
												   AbstractConcursoService<ConcursoLotoFacil> concursoService,
									   @Qualifier("parseContentFileLotoFacilServiceImpl")
											   ParserContentFileService<ConcursoLotoFacil> parseContentFileServiceImpl){

		super(concursoService, parseContentFileServiceImpl);
	}
}