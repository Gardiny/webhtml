package com.softskills.softskills.service;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.controller.dto.AlunoSkillDto;
import com.softskills.softskills.model.Aluno;
import com.softskills.softskills.model.AlunoSkill;
import com.softskills.softskills.model.AlunoSkillId;
import com.softskills.softskills.model.DisciplinaSkill;
import com.softskills.softskills.model.Skill;
import com.softskills.softskills.model.TurmaAluno;
import com.softskills.softskills.model.TurmaDisciplina;
import com.softskills.softskills.repository.AlunoRepository;
import com.softskills.softskills.repository.AlunoSkillRepository;
import com.softskills.softskills.repository.DisciplinaSkillRepository;
import com.softskills.softskills.repository.SkillRepository;
import com.softskills.softskills.repository.TurmaAlunoRepository;
import com.softskills.softskills.repository.TurmaDisciplinaRepository;

@Service
public class AlunoSkillService {

    private final AlunoSkillRepository repo;
    private final AlunoRepository alunoRepo;
    private final SkillRepository skillRepo;
    private final TurmaAlunoRepository turmaAlunoRepo;
    private final DisciplinaSkillRepository discSkillRepo;
    private final TurmaDisciplinaRepository turmDiscRepo;

    public AlunoSkillService (
        AlunoSkillRepository repo,
        AlunoRepository alunoRepo,
        SkillRepository skillRepo,
        TurmaAlunoRepository turmaAlunoRepo,
        DisciplinaSkillRepository discSkillRepo,
        TurmaDisciplinaRepository turmDiscRepo){
        this.repo = repo;
        this.alunoRepo = alunoRepo;
        this.skillRepo = skillRepo;
        this.turmaAlunoRepo = turmaAlunoRepo;
        this.discSkillRepo = discSkillRepo;
        this.turmDiscRepo = turmDiscRepo;
    }

    public Page<AlunoSkill> get(Pageable page){
        return repo.findAll(page);
    }

    public AlunoSkill get(AlunoSkillId id) {
        return repo.findById(id).orElse(null);
    }

    public List<AlunoSkill> getByAluno(Long id){
        return repo.findByAluno(id);
    }

    public List<AlunoSkill> getBySkill(Long id){
        return repo.findBySkill(id);
    }

    public AlunoSkill save(AlunoSkillDto dto) {
        Aluno aluno = alunoRepo.findById(dto.aluno_id()).orElse(null);
        Skill skill = skillRepo.findById(dto.skill_id()).orElse(null);
        AlunoSkillId id = new AlunoSkillId(dto.aluno_id(),dto.skill_id());
        AlunoSkill registro = new AlunoSkill();
        registro.setId(id);
        registro.setAluno(aluno);
        registro.setSkill(skill);
        if(dto.nota_final() != null){
            registro.setNota_final(dto.nota_final());
        }
        return repo.save(registro);
    }

    public void delete(AlunoSkillId id) {
        repo.deleteById(id); 
    }

    // preneche turma aluno de um aluno especifico ao ser chamado
    public void preencher(Long turmaId, Long alunoId){
        // List<Disciplina> disciplinas = discRepo.findByTurmaId(turmaId);
            List<TurmaDisciplina> turmaDisciplina = turmDiscRepo.getByTurma(turmaId);
            for (TurmaDisciplina disciplina : turmaDisciplina){
                List<DisciplinaSkill> skills = discSkillRepo.getByDisciplinaId(disciplina.getDisciplina().getId());
                for (DisciplinaSkill skill : skills){
                    AlunoSkill existe = repo.findByIds(alunoId,skill.getSkill().getId());
                    if(existe == null){
                        AlunoSkill alunoSkill = new AlunoSkill();
                        alunoSkill.setAluno(alunoRepo.findById(alunoId).orElse(null));
                        alunoSkill.setSkill(skill.getSkill());
                        repo.save(alunoSkill);
                    }
                }
            
            }
    }

    // Preenche a tabela automaticamente quando a aplicação inicia 
    @EventListener(ApplicationReadyEvent.class)
    public void autoPreencher(){
        List<TurmaAluno> turmaAlunos = turmaAlunoRepo.findAll();
        for (TurmaAluno turmaAluno : turmaAlunos) {
            Long turmaId = turmaAluno.getTurma().getId();
            Long alunoId = turmaAluno.getAluno().getId();
            List<TurmaDisciplina> turmaDisciplina = turmDiscRepo.getByTurma(turmaId);
            for (TurmaDisciplina disciplina : turmaDisciplina){
                List<DisciplinaSkill> skills = discSkillRepo.getByDisciplinaId(disciplina.getDisciplina().getId());
                for (DisciplinaSkill skill : skills){
                    AlunoSkill existe = repo.findByIds(alunoId,skill.getSkill().getId());
                    if(existe == null){
                        AlunoSkill alunoSkill = new AlunoSkill();
                        alunoSkill.setAluno(turmaAluno.getAluno());
                        alunoSkill.setSkill(skill.getSkill());
                        repo.save(alunoSkill);
                    }
                }
            
            }
        }
    }
}
