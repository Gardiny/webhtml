package com.softskills.softskills.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softskills.softskills.model.DisciplinaSkill;
import com.softskills.softskills.model.DisciplinaSkillId;
import java.util.List;


@Repository
public interface DisciplinaSkillRepository extends JpaRepository<DisciplinaSkill, DisciplinaSkillId>{

    @Query(
        "SELECT ds FROM DisciplinaSkill ds WHERE ds.disciplina.id = :disciplinaId"
    )
    List<DisciplinaSkill> findByDisciplinaId(Long disciplinaId);
}
