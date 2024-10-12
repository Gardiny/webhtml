package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.softskills.softskills.model.Disciplina;


public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    @Query(
            "SELECT d FROM Disciplina d WHERE d.nome LIKE %?1%" +
            " OR d.carga_horaria = ?2"
    )
    Page<Disciplina> busca(String termoBusca, Pageable page);

}
