package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Disciplina;
import com.softskills.softskills.repository.DisciplinaRepository;

@Service
public class DisciplinaService implements Iservice<Disciplina> {

//Falta implementar Cache

    private final DisciplinaRepository repo;

    public DisciplinaService(DisciplinaRepository repo){
        this.repo = repo;
    }

    @Override
    public Page<Disciplina> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Disciplina get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Disciplina save(Disciplina objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
