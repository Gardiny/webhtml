package com.softskills.softskills.service;

import java.lang.annotation.Target;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.AutoAvaliacao;
import com.softskills.softskills.model.DisciplinaSkill;
import com.softskills.softskills.model.TurmaAluno;
import com.softskills.softskills.model.TurmaDisciplina;
import com.softskills.softskills.repository.AlunoSkillRepository;
import com.softskills.softskills.repository.AutoAvaliacaoRepository;
import com.softskills.softskills.repository.DisciplinaSkillRepository;
import com.softskills.softskills.repository.TurmaAlunoRepository;
import com.softskills.softskills.repository.TurmaDisciplinaRepository;

@Service
public class AutoAvaliacaoService implements Iservice<AutoAvaliacao>{
    
    private AutoAvaliacaoRepository repo;
    private MetricaNotaService MServico;
    private TurmaDisciplinaRepository TDrepo;
    private TurmaAlunoRepository TArepo;
    private DisciplinaSkillRepository DSrepo;
    private AlunoSkillRepository ASrepo;

    public AutoAvaliacaoService
    (AutoAvaliacaoRepository repo,
    MetricaNotaService MServico,
    TurmaDisciplinaRepository TDrepo,
    TurmaAlunoRepository TArepo,
    DisciplinaSkillRepository DSrepo,
    AlunoSkillRepository ASrepo){
        this.repo = repo;
        this.MServico = MServico;
        this.TDrepo = TDrepo;
        this.TArepo = TArepo;
        this.DSrepo = DSrepo;
        this.ASrepo = ASrepo;
    }

    @Override
    public Page<AutoAvaliacao> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public AutoAvaliacao get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public AutoAvaliacao save(AutoAvaliacao objeto) {
        Long alunoId = objeto.getAluno().getId();
        Long SkillId = objeto.getSkill().getId();
        AutoAvaliacao resposta = repo.save(objeto);
        MServico.media(alunoId, SkillId);
        return resposta;
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    // geração automatica de autoavaliações para fins de teste, descomente para implementear
    
}
