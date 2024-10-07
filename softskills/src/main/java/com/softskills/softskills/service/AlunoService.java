package com.softskills.softskills.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Aluno;
import com.softskills.softskills.repository.AlunoRepository;

@Service
public class AlunoService implements Iservice<Aluno>{

    //Falta implementar Cache

    private final AlunoRepository repo;

    public AlunoService(AlunoRepository repo){
        this.repo = repo;
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
        return repo.save(objeto);
    }

    @Override
    @CacheEvict(value = "aluno", key = "#id")
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
