package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.softskills.softskills.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query(
        "SELECT p FROM Professor p WHERE p.nome LIKE %?1%" +
        " OR p.email LIKE %?1%" +
        " OR p.telefone LIKE %?1%"
    )
    Page<Professor> busca(String termoBusca, Pageable page);
    
}
