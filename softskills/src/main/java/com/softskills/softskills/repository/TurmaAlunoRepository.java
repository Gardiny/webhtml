package com.softskills.softskills.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softskills.softskills.model.TurmaAluno;
import com.softskills.softskills.model.TurmaAlunoId;

@Repository
public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno,TurmaAlunoId>{

    @Query(
        "select ta from TurmaAluno ta where ta.turma.id = :turmaId"
    )
    List<TurmaAluno> getByTurma(Long turmaId);

    @Query(
        "select ta from TurmaAluno ta where ta.aluno.id = :alunoId"
    )
    List<TurmaAluno> getByAluno(Long alunoId);
}