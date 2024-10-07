package com.softskills.softskills.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class DisciplinaSkillId implements Serializable{

    private Long disciplinaId;
    private Long skillId;
    
    public Long getDisciplinaId() {
        return disciplinaId;
    }
    public void setDisciplinaId(Long disciplina) {
        this.disciplinaId = disciplina;
    }
    public Long getSkillId() {
        return skillId;
    }
    public void setSkillId(Long skill) {
        this.skillId = skill;
    }

    public DisciplinaSkillId(){}

    public DisciplinaSkillId(Long disciplina, Long skill) {
        this.disciplinaId = disciplina;
        this.skillId = skill;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((disciplinaId == null) ? 0 : disciplinaId.hashCode());
        result = prime * result + ((skillId == null) ? 0 : skillId.hashCode());
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
        DisciplinaSkillId other = (DisciplinaSkillId) obj;
        if (disciplinaId == null) {
            if (other.disciplinaId != null)
                return false;
        } else if (!disciplinaId.equals(other.disciplinaId))
            return false;
        if (skillId == null) {
            if (other.skillId != null)
                return false;
        } else if (!skillId.equals(other.skillId))
            return false;
        return true;
    }

    
}
