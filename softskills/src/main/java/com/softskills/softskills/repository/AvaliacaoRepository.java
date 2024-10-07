package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softskills.softskills.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

    @Query(
        "SELECT a FROM Avaliacao a JOIN a.professor p JOIN a.aluno al"+
        " JOIN a.disciplina d JOIN a.skill s"+
        " WHERE p.nome LIKE %?1% OR al.nome LIKE %?1% or d.nome LIKE %?1% or s.nome LIKE %?1%"
    )
    Page<Avaliacao> busca(String termoBusca, Pageable page);
}
