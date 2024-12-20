package com.softskills.softskills.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softskills.softskills.model.AutoAvaliacao;

@Repository
public interface AutoAvaliacaoRepository extends JpaRepository<AutoAvaliacao, Long>{

    @Query(
        "select aa from AutoAvaliacao aa join aa.aluno al " +
        "join aa.disciplina d join aa.skill s "+
        "where al.nome like %?1% or d.nome like %?1% or s.nome like %?1%"
    )
    Page<AutoAvaliacao> busca(String termoBusca, Pageable page);

        @Query(
        "SELECT aa FROM AutoAvaliacao aa JOIN aa.aluno al"+
        " JOIN aa.skill s"+
        " WHERE al.id = :alunoId and s.id = :skillId"
    )
    List<AutoAvaliacao> buscaPrecisa(Long alunoId, Long skillId);
}
