package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.softskills.softskills.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query(
        "SELECT t FROM Turma t JOIN t.capacitacao c WHERE t.quant_vagas = ?1" +
        " OR c.nome LIKE %?2%"
    )

    Page<Turma> busca(String termoBusca, Pageable page);
}
