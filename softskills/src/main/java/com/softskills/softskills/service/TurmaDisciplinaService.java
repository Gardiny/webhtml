package com.softskills.softskills.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.controller.dto.TurmaDisciplinaDto;
import com.softskills.softskills.model.Disciplina;
import com.softskills.softskills.model.Professor;
import com.softskills.softskills.model.Turma;
import com.softskills.softskills.model.TurmaDisciplina;
import com.softskills.softskills.model.TurmaDisciplinaId;
import com.softskills.softskills.repository.DisciplinaRepository;
import com.softskills.softskills.repository.ProfessorRepository;
import com.softskills.softskills.repository.TurmaDisciplinaRepository;
import com.softskills.softskills.repository.TurmaRepository;

@Service
public class TurmaDisciplinaService {

    private final TurmaDisciplinaRepository repo;
    private final TurmaRepository turmaRepo;
    private final DisciplinaRepository discRepo;
    private final ProfessorRepository profRepo;

    public TurmaDisciplinaService(
        TurmaDisciplinaRepository repo,
        TurmaRepository turmaRepo,
        DisciplinaRepository discRepo,
        ProfessorRepository profRepo
    ){
        this.repo = repo;
        this.turmaRepo = turmaRepo;
        this.discRepo = discRepo;
        this.profRepo = profRepo;
    }

    public Page<TurmaDisciplina> get(Pageable page){
        return repo.findAll(page);
    }

    public TurmaDisciplina get(TurmaDisciplinaId id){
        return repo.findById(id).orElse(null);
    }

    public List<TurmaDisciplina> getByTurma(Long id){
        return repo.getByTurma(id);
    }

    public List<TurmaDisciplina> getByDisciplina(Long id){
        return repo.getByDisciplina(id);
    }

    public List<TurmaDisciplina> getByProfessor(Long id){
        return repo.getByProfessor(id);
    }

    public TurmaDisciplina save(TurmaDisciplinaDto dto) {
        Turma turma = turmaRepo.findById(dto.turma_id()).orElse(null);
        Disciplina disciplina = discRepo.findById(dto.disciplina_id()).orElse(null);
        Professor professor = profRepo.findById(dto.professor_id()).orElse(null);
        TurmaDisciplinaId id = new TurmaDisciplinaId(dto.turma_id(),dto.disciplina_id(),dto.professor_id());
        TurmaDisciplina registro = new TurmaDisciplina();
        registro.setId(id);
        registro.setTurma(turma);
        registro.setDisciplina(disciplina);
        registro.setProfessor(professor);
        return repo.save(registro);
    }

    public void delete(TurmaDisciplinaId id) {
        repo.deleteById(id);
    }
}
