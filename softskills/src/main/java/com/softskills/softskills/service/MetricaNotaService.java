package com.softskills.softskills.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softskills.softskills.controller.dto.AlunoSkillDto;
import com.softskills.softskills.model.AutoAvaliacao;
import com.softskills.softskills.model.Avaliacao;
import com.softskills.softskills.model.ENota;
import com.softskills.softskills.repository.AutoAvaliacaoRepository;
import com.softskills.softskills.repository.AvaliacaoRepository;

@Service
public class MetricaNotaService {

    private final AvaliacaoRepository Arepo;
    private final AutoAvaliacaoRepository AArepo;
    private final AlunoSkillService ASServico;

    public MetricaNotaService (
        AvaliacaoRepository Arepo,
        AutoAvaliacaoRepository AArepo,
        AlunoSkillService ASServico
    ){
        this.Arepo = Arepo;
        this.AArepo = AArepo;
        this.ASServico = ASServico;
    }

    // Mudar depois para quando a avaliação for de 1 a 5
    public void media(Long alunoId, Long skillId){
        // Pega as avaliações e autoavaliações de um aluno e skill especifica
        List<Avaliacao> avaliacoes = Arepo.buscaPrecisa(alunoId, skillId);
        List<AutoAvaliacao> autoAvaliacoes = AArepo.buscaPrecisa(alunoId, skillId);
        // Verifica se a avaliação e autoAvaliação não estão vazias
        if (!avaliacoes.isEmpty() && !autoAvaliacoes.isEmpty()){
            Integer somaA = 0;
            Integer somaAA = 0;
            // Pega as notas das avaliações
            for (Avaliacao avaliacao: avaliacoes){
                somaA = avaliacao.getNota();
            }
            // Pega as notas das AutoAvaliações
            for (AutoAvaliacao autoAvaliacao: autoAvaliacoes){
                somaAA = autoAvaliacao.getNota();
            }
            // Faz a media
            Float media = (float) 0.0;
            // Numero de avaliações como Nao avaliado (Retirado, Não possui mais Não avaliado)
            // Numero de autoavaliações como Não avaliado (Retirado, vai de 0 á 10)
            media =  (float) ( (somaA / (avaliacoes.size())) * 0.7 + (somaAA / (autoAvaliacoes.size() )) * 0.3);
            AlunoSkillDto dto = new AlunoSkillDto(alunoId, null, skillId, null, null, media.toString());
            ASServico.save(dto);
        }
    }
}
