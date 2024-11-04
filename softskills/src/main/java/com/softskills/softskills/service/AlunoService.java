package com.softskills.softskills.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Aluno;
import com.softskills.softskills.model.EPapel;
import com.softskills.softskills.model.Usuario;
import com.softskills.softskills.repository.AlunoRepository;

@Service
public class AlunoService implements Iservice<Aluno>{

    //Falta implementar Cache

    private final AlunoRepository repo;
    private final UsuarioService Uservice;

    public AlunoService(
        AlunoRepository repo,
        UsuarioService Uservice){
        this.repo = repo;
        this.Uservice = Uservice;
    }

    @Override
    public Page<Aluno> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Aluno get(Long id) {
        return repo.findById(id).orElse(null); // null para evitar erro de retorno vazio
    }

    @Override
    public Aluno save(Aluno objeto) {
        String nome_completo = objeto.getNome();
        String nome_usuario = objeto.getEmail();
        String email = objeto.getEmail();
        String senha = objeto.getNome().split(" ")[0] + "@2024";
        EPapel papel = EPapel.ALUNO;
        Boolean ativo = true;
        Usuario usuario = new Usuario();
        usuario.setNome_completo(nome_completo);
        usuario.setNome_usuario(nome_usuario);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setPapel(papel);
        usuario.setAtivo(ativo);
        Uservice.save(usuario);
        objeto.setUsuario(usuario);
        return repo.save(objeto);
    }

    @Override
    // @CacheEvict(value = "aluno", key = "#id")
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
