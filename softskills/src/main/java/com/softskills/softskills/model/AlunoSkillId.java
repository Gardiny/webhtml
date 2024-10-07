package com.softskills.softskills.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class AlunoSkillId implements Serializable{

    private Long aluno;
    private Long skill;
    
    public AlunoSkillId(Long aluno, Long skill) {
        this.aluno = aluno;
        this.skill = skill;
    }

    public AlunoSkillId() {
    }

    public Long getAluno() {
        return aluno;
    }

    public void setAluno(Long aluno) {
        this.aluno = aluno;
    }

    public Long getSkill() {
        return skill;
    }

    public void setSkill(Long skill) {
        this.skill = skill;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
        result = prime * result + ((skill == null) ? 0 : skill.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AlunoSkillId other = (AlunoSkillId) obj;
        if (aluno == null) {
            if (other.aluno != null)
                return false;
        } else if (!aluno.equals(other.aluno))
            return false;
        if (skill == null) {
            if (other.skill != null)
                return false;
        } else if (!skill.equals(other.skill))
            return false;
        return true;
    }

    
}
