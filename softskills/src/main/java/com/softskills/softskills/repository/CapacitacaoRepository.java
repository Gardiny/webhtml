package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.softskills.softskills.model.Capacitacao;

public interface CapacitacaoRepository extends JpaRepository<Capacitacao, Long> {

    @Query(
        "SELECT c FROM Capacitacao c WHERE c.nome LIKE %?1%" +
        " OR c.carga_horaria LIKE %?1%"
    )
    Page<Capacitacao> busca(String termoBusca, Pageable page);
    
}
