package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.controller.dto.TurmaAlunoDto;
import com.softskills.softskills.model.Aluno;
import com.softskills.softskills.model.Turma;
import com.softskills.softskills.model.TurmaAluno;
import com.softskills.softskills.model.TurmaAlunoId;
import com.softskills.softskills.repository.AlunoRepository;
import com.softskills.softskills.repository.TurmaAlunoRepository;
import com.softskills.softskills.repository.TurmaRepository;

@Service
public class TurmaAlunoService {

    private final TurmaAlunoRepository repo;
    private final TurmaRepository turmaRepo;
    private final AlunoRepository alunoRepo;
    private final AlunoSkillService servicoAS;

    public TurmaAlunoService(
        TurmaAlunoRepository repo,
        TurmaRepository turmaRepo,
        AlunoRepository alunoRepo,
        AlunoSkillService servicoAS){
        this.repo = repo;
        this.alunoRepo = alunoRepo;
        this.turmaRepo = turmaRepo;
        this.servicoAS = servicoAS;
    }

    public Page<TurmaAluno> get(Pageable page){
        return repo.findAll(page);
    }

    public TurmaAluno get(TurmaAlunoId id) {
        return repo.findById(id).orElse(null);
    }

    public TurmaAluno save(TurmaAlunoDto dto) {
        Turma turma = turmaRepo.findById(dto.turma_id()).orElse(null);
        Aluno aluno = alunoRepo.findById(dto.aluno_id()).orElse(null);
        TurmaAlunoId id = new TurmaAlunoId(dto.turma_id(),dto.aluno_id());
        TurmaAluno registro = new TurmaAluno();
        registro.setId(id);
        registro.setAluno(aluno);
        registro.setTurma(turma);
        servicoAS.preencher(dto.turma_id(), dto.aluno_id());
        return repo.save(registro);
    }

    public void delete(TurmaAlunoId id) {
        repo.deleteById(id); 
    }
}
