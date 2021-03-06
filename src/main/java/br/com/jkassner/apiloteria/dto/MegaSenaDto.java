package br.com.jkassner.apiloteria.dto;

import br.com.jkassner.apiloteria.model.Cidade;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MegaSenaDto {

    private Long idConcurso;
    private BigDecimal vlAcumulado;
    private BigDecimal vlAcumuladoMegaVirada;
    private Date dtSorteio;
    private BigDecimal vlEstimativaPremio;
    private int nrGanhadoresQuina;
    private int nrGanhadoresSena;
    private int nrGanhadoresQuadra;
    private int prDezena;
    private int seDezena;
    private int teDezena;
    private int qaDezena;
    private int qiDezena;
    private int sxDezena;
    private BigDecimal vlRateioSena;
    private BigDecimal vlRateioQuina;
    private BigDecimal vlRateioQuadra;
    private boolean acumulado;
    private BigDecimal vlArrecadacaoTotal;
    private List<Cidade> cidades = new ArrayList<>();
}
