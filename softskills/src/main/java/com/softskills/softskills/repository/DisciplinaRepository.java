package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softskills.softskills.model.Disciplina;
import java.util.List;


public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    @Query(
            "SELECT d FROM Disciplina d JOIN d.professor p WHERE d.nome LIKE %?1%" +
            " OR d.carga_horaria = ?2" +
            " OR p.nome LIKE %?3%"
    )
    Page<Disciplina> busca(String termoBusca, Pageable page);
    
    @Query(
        "SELECT d FROM Disciplina d WHERE d.turma.id = :turmaId"
    )
    List<Disciplina> findByTurmaId(Long turmaId);
}
