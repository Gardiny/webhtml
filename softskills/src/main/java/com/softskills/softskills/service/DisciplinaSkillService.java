package com.softskills.softskills.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.controller.dto.DisciplinaSkillDto;
import com.softskills.softskills.model.Disciplina;
import com.softskills.softskills.model.DisciplinaSkill;
import com.softskills.softskills.model.DisciplinaSkillId;
import com.softskills.softskills.model.Skill;
import com.softskills.softskills.repository.DisciplinaRepository;
import com.softskills.softskills.repository.DisciplinaSkillRepository;
import com.softskills.softskills.repository.SkillRepository;

@Service
public class DisciplinaSkillService{

    private final DisciplinaSkillRepository repo;
    private final DisciplinaRepository discRepo;
    private final SkillRepository skillRepo;

    public DisciplinaSkillService (
        DisciplinaSkillRepository repo,
        DisciplinaRepository discRepo,
        SkillRepository skillRepo){
        this.repo = repo;
        this.discRepo = discRepo;
        this.skillRepo = skillRepo;
    }

    public Page<DisciplinaSkill> get(Pageable page) {
        return repo.findAll(page);
    }

    public DisciplinaSkill get(DisciplinaSkillId id) {
        return repo.findById(id).orElse(null);
    }

    public List<DisciplinaSkill> getByDisciplinaId(Long id){
        return repo.getByDisciplinaId(id);
    }

    public List<DisciplinaSkill> getBySkillId(Long id){
        return repo.getBySkillId(id);
    }

    public DisciplinaSkill save(DisciplinaSkillDto dto) {
        Disciplina disciplina = discRepo.findById(dto.disciplina_id()).orElse(null);
        Skill skill = skillRepo.findById(dto.skill_id()).orElse(null);
        DisciplinaSkillId id = new DisciplinaSkillId(dto.disciplina_id(),dto.skill_id());
        DisciplinaSkill registro = new DisciplinaSkill();
        registro.setId(id);
        registro.setDisciplina(disciplina);
        registro.setSkill(skill);
        return repo.save(registro);
    }

    public void delete(DisciplinaSkillId id) {
        repo.deleteById(id);
    }

}
