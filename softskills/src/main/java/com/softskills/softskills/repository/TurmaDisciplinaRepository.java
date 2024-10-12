package com.softskills.softskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softskills.softskills.model.TurmaDisciplina;
import com.softskills.softskills.model.TurmaDisciplinaId;

import java.util.List;

@Repository
public interface TurmaDisciplinaRepository extends JpaRepository<TurmaDisciplina,TurmaDisciplinaId>{
    @Query(
        "select td from TurmaDisciplina td where td.turma.id = :turmaId"
    )
    List<TurmaDisciplina> getByTurma(Long turmaId);

    @Query(
        "select td from TurmaDisciplina td where td.disciplina.id = :disciplinaId"
    )
    List<TurmaDisciplina> getByDisciplina(Long disciplina);

    @Query(
        "select td from TurmaDisciplina td where td.professor.id = :professorId"
    )
    List<TurmaDisciplina> getByProfessor(Long professor);
}

