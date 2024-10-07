package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Professor;
import com.softskills.softskills.repository.ProfessorRepository;

@Service
public class ProfessorService implements Iservice<Professor>{

//Falta implementar Cache

    private final ProfessorRepository repo;

    public ProfessorService(ProfessorRepository repo){
        this.repo = repo;
    }

    @Override
    public Page<Professor> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Professor get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Professor save(Professor objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
