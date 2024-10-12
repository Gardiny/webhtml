package com.softskills.softskills.model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class TurmaAluno implements Serializable{
    
    @EmbeddedId
    private TurmaAlunoId id;
    
    @ManyToOne
    @MapsId("turma")
    private Turma turma;

    @ManyToOne
    @MapsId("aluno")
    private Aluno aluno;

    public TurmaAluno() {
    }

    public TurmaAluno(Turma turma, Aluno aluno) {
        this.id = new TurmaAlunoId(turma.getId(),aluno.getId());
        this.turma = turma;
        this.aluno = aluno;
    }

    public TurmaAlunoId getId() {
        return id;
    }

    public void setId(TurmaAlunoId id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
        if (turma != null){
            if (id == null) id = new TurmaAlunoId();
            id.setTurma(turma.getId());
        }
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        if (aluno != null){
            if (id == null) id = new TurmaAlunoId();
            id.setAluno(aluno.getId());
        }
    }
}
