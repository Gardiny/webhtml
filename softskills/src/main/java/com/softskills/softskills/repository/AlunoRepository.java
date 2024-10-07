

package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.softskills.softskills.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(
        "SELECT a FROM Aluno a WHERE a.nome LIKE %?1%" +
        " OR a.email LIKE %?1%" +
        " OR a.telefone LIKE %?1%" +
        " OR a.status LIKE %?1%"
    )
    Page<Aluno> busca(String termoBusca, Pageable page);
    
}
