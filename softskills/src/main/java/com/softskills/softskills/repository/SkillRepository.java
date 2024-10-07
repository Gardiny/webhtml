package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.softskills.softskills.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query(
        "SELECT s FROM Skill s WHERE s.nome LIKE %?1%" +
        " OR s.descricao LIKE %?1%"
    )
    Page<Skill> busca(String termoBusca, Pageable page);
    
}
