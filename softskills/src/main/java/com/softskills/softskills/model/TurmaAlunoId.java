package com.softskills.softskills.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class TurmaAlunoId implements Serializable{

    private Long turma;
    private Long aluno;
    
    public TurmaAlunoId() {
    }

    public TurmaAlunoId(Long turma, Long aluno) {
        this.turma = turma;
        this.aluno = aluno;
    }

    public Long getTurma() {
        return turma;
    }

    public void setTurma(Long turma) {
        this.turma = turma;
    }

    public Long getAluno() {
        return aluno;
    }

    public void setAluno(Long aluno) {
        this.aluno = aluno;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((turma == null) ? 0 : turma.hashCode());
        result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
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
        TurmaAlunoId other = (TurmaAlunoId) obj;
        if (turma == null) {
            if (other.turma != null)
                return false;
        } else if (!turma.equals(other.turma))
            return false;
        if (aluno == null) {
            if (other.aluno != null)
                return false;
        } else if (!aluno.equals(other.aluno))
            return false;
        return true;
    }


    
}
