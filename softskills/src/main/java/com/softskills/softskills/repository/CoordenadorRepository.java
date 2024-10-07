package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.softskills.softskills.model.Coordenador;

public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {

    @Query(
        "SELECT c FROM Coordenador c WHERE c.nome LIKE %?1%" +
        " OR c.email LIKE %?1%" +
        " OR c.telefone LIKE %?1%"
    )
    Page<Coordenador> busca(String termoBusca, Pageable page);
    
}
