package com.softskills.softskills.model;


import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class DisciplinaSkill implements Serializable{

    @EmbeddedId
    private DisciplinaSkillId id;

    @ManyToOne
    @MapsId("disciplinaId")
    private Disciplina disciplina;

    @ManyToOne
    @MapsId("skillId")
    private Skill skill;
    
    public DisciplinaSkill(){}

    public DisciplinaSkill(Disciplina disciplina, Skill skill) {
        this.id = new DisciplinaSkillId(disciplina.getId(), skill.getId());
        this.disciplina = disciplina;
        this.skill = skill;
    }

    public DisciplinaSkillId getId() {
        return id;
    }

    public void setId(DisciplinaSkillId id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
        if (disciplina != null){
            if (id == null) id = new DisciplinaSkillId();
            id.setDisciplinaId(disciplina.getId());
        }
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
        if (skill != null){
            if(id == null) id = new DisciplinaSkillId();
            id.setSkillId(skill.getId());
        }
    }

}
