package com.softskills.softskills.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softskills.softskills.model.AlunoSkill;
import com.softskills.softskills.model.AlunoSkillId;


@Repository
public interface AlunoSkillRepository extends JpaRepository<AlunoSkill, AlunoSkillId>{

    @Query(
        "SELECT as FROM AlunoSkill as WHERE as.aluno.id = :alunoId AND as.skill.id = :skillId"
    )
    AlunoSkill findByIds(Long alunoId, Long skillId);

    @Query(
        "SELECT as FROM AlunoSkill as WHERE as.aluno.id = :alunoId"
    )
    List<AlunoSkill> findByAluno(Long alunoId);

    @Query(
        "Select as from AlunoSkill as where as.skill.id = :skillId"
    )
    List<AlunoSkill> findBySkill(Long skillId);
}
