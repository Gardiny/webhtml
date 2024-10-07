package com.softskills.softskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softskills.softskills.model.TurmaAluno;
import com.softskills.softskills.model.TurmaAlunoId;

@Repository
public interface TurmaAlunoRepository extends JpaRepository<TurmaAluno,TurmaAlunoId>{

}