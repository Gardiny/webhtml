package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Avaliacao;
import com.softskills.softskills.repository.AlunoRepository;
import com.softskills.softskills.repository.AlunoSkillRepository;
import com.softskills.softskills.repository.AvaliacaoRepository;
import com.softskills.softskills.repository.DisciplinaRepository;
import com.softskills.softskills.repository.DisciplinaSkillRepository;
import com.softskills.softskills.repository.ProfessorRepository;
import com.softskills.softskills.repository.SkillRepository;
import com.softskills.softskills.repository.TurmaAlunoRepository;
import com.softskills.softskills.repository.TurmaDisciplinaRepository;

@Service
public class AvaliacaoService implements Iservice<Avaliacao>{

    private AvaliacaoRepository repo;
    private MetricaNotaService MServico;

    public AvaliacaoService(
        AvaliacaoRepository repo,
        MetricaNotaService MServico,
        TurmaDisciplinaRepository TDrepo,
        TurmaAlunoRepository TArepo,
        DisciplinaSkillRepository DSrepo,
        AlunoSkillRepository ASrepo,
        DisciplinaRepository Drepo,
        SkillRepository Srepo,
        ProfessorRepository Prepo,
        AlunoRepository Arepo){
        this.repo = repo;
        this.MServico = MServico;
    }

    @Override
    public Page<Avaliacao> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Avaliacao get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Avaliacao save(Avaliacao objeto) {
        Long alunoId = objeto.getAluno().getId();
        Long skillId = objeto.getSkill().getId();
        Avaliacao resposta = repo.save(objeto);
        MServico.media(alunoId, skillId);
        return resposta;
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    // Gerar aleatoriamente avaliações, para fins de testes
    // Pelo bem do seu computador, não des-comente
    // @EventListener(ApplicationReadyEvent.class)
    // public void autoAvaliacaoAutomatica(){
    //     ENota[] lista = ENota.values();
    //     Random random = new Random();
    //     List<TurmaDisciplina> turmaDisciplinas = TDrepo.findAll();
    //     List<TurmaAluno> turmaAlunos = TArepo.findAll();
    //     List<DisciplinaSkill> disciplinaSkills = DSrepo.findAll();
    //     for (TurmaDisciplina turmaDisciplina: turmaDisciplinas){
    //         Long professorId = turmaDisciplina.getProfessor().getId();
    //         Long disciplinaId = turmaDisciplina.getDisciplina().getId();
    //         Long turmaId = turmaDisciplina.getTurma().getId();
    //         for(TurmaAluno turmaAluno : turmaAlunos){
    //             if(turmaAluno.getTurma().getId() == turmaId){
    //                 Long alunoId = turmaAluno.getAluno().getId();
    //                 for(DisciplinaSkill disciplinaSkill: disciplinaSkills){
    //                     Long skillId = disciplinaSkill.getSkill().getId();
    //                     Avaliacao registro = new Avaliacao();
    //                     registro.setData(LocalDateTime.now());
    //                     registro.setDisciplina(Drepo.findById(disciplinaId).orElse(null));
    //                     registro.setProfessor(Prepo.findById(professorId).orElse(null));
    //                     registro.setSkill(Srepo.findById(skillId).orElse(null));
    //                     registro.setAluno(Arepo.findById(alunoId).orElse(null));
    //                     int inota = random.nextInt(lista.length);
    //                     ENota nota = lista[inota];
    //                     registro.setNota(nota);
    //                     save(registro);
    //             }

    //         }

    //         }
    //     }
    // }
}
