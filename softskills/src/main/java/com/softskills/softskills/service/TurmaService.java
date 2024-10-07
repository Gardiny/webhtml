package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Turma;
import com.softskills.softskills.repository.TurmaRepository;

@Service
public class TurmaService implements Iservice<Turma>{

//Falta implementar Cache

    private final TurmaRepository repo;

    public TurmaService(TurmaRepository repo){
        this.repo = repo;
    }

    @Override
    public Page<Turma> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Turma get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Turma save(Turma objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
