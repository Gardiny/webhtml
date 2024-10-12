package com.softskills.softskills.model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class TurmaDisciplina implements Serializable{
    
    @EmbeddedId
    private TurmaDisciplinaId id;
    
    @ManyToOne
    @MapsId("turma")
    private Turma turma;

    @ManyToOne
    @MapsId("disciplina")
    private Disciplina disciplina;

    @ManyToOne
    @MapsId("professor")
    private Professor professor;

    public TurmaDisciplina() {
    }

    public TurmaDisciplina(Turma turma, Disciplina disciplina, Professor professor) {
        this.id = new TurmaDisciplinaId(turma.getId(),disciplina.getId(),professor.getId());
        this.turma = turma;
        this.disciplina = disciplina;
        this.professor = professor;
    }

    public TurmaDisciplinaId getId() {
        return id;
    }

    public void setId(TurmaDisciplinaId id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
        if (turma != null){
            if (id == null) id = new TurmaDisciplinaId();
            id.setTurma(turma.getId());
        }
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina Disciplina) {
        this.disciplina = Disciplina;
        if (Disciplina != null){
            if (id == null) id = new TurmaDisciplinaId();
            id.setDisciplina(Disciplina.getId());
        }
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
        if (professor != null){
            if (id == null) id = new TurmaDisciplinaId();
            id.setProfessor(professor.getId());
        }
    }
}
