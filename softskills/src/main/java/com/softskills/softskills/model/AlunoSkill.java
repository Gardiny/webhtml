package com.softskills.softskills.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class AlunoSkill implements Serializable{

    @EmbeddedId
    private AlunoSkillId id;

    @ManyToOne
    @MapsId("aluno")
    private Aluno aluno;

    @ManyToOne
    @MapsId("skill")
    private Skill skill;

    @Column
    private String nota_final;

    public AlunoSkill() {
    }

    public AlunoSkill(Aluno aluno, Skill skill) {
        this.id = new AlunoSkillId(aluno.getId(),skill.getId());
        this.aluno = aluno;
        this.skill = skill;
    }

    public AlunoSkillId getId() {
        return id;
    }

    public void setId(AlunoSkillId id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        if (aluno != null){
            if (id == null) id = new AlunoSkillId();
            id.setAluno(aluno.getId());
        }
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
        if (skill != null){
            if(id == null) id = new AlunoSkillId();
            id.setSkill(skill.getId());
        }
    }

    public String getNota_final() {
        return nota_final;
    }

    public void setNota_final(String nota_final) {
        this.nota_final = nota_final;
    }


    

}
