package com.softskills.softskills.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class TurmaDisciplinaId implements Serializable{

    private Long turma;
    private Long disciplina;
    private Long professor;
    
    public TurmaDisciplinaId() {
    }

    public TurmaDisciplinaId(Long turma, Long disciplina, Long professor) {
        this.turma = turma;
        this.disciplina = disciplina;
        this.professor = professor;
    }

    public Long getTurma() {
        return turma;
    }

    public void setTurma(Long turma) {
        this.turma = turma;
    }

    public Long getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Long disciplina) {
        this.disciplina = disciplina;
    }

    public Long getProfessor() {
        return professor;
    }

    public void setProfessor(Long professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((turma == null) ? 0 : turma.hashCode());
        result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
        result = prime * result + ((professor == null) ? 0 : professor.hashCode());
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
        TurmaDisciplinaId other = (TurmaDisciplinaId) obj;
        if (turma == null) {
            if (other.turma != null)
                return false;
        } else if (!turma.equals(other.turma))
            return false;
        if (disciplina == null) {
            if (other.disciplina != null)
                return false;
        } else if (!disciplina.equals(other.disciplina))
            return false;
        if (professor == null) {
            if (other.professor != null)
                return false;
        } else if (!professor.equals(other.professor))
            return false;
        return true;
    }





    
}
