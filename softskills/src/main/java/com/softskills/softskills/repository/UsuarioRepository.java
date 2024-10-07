package com.softskills.softskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // >> Importando - Pageable
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;
import com.softskills.softskills.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(
        "SELECT p FROM Usuario p " +
        "WHERE p.nome_completo LIKE %?1% " +
        "OR p.nome_usuario LIKE %?1%"
    )
    Page<Usuario> busca(String termoBusca, Pageable page);

    @Query("SELECT p FROM Usuario p " + "WHERE p.nome_usuario =?1")
    Usuario findByNomeUsuario(String nomeUsuario);
}
