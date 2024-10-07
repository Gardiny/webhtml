package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Coordenador;
import com.softskills.softskills.repository.CoordenadorRepository;

@Service
public class CoordenadorService implements Iservice<Coordenador>{

//Falta implementar Cache

    private final CoordenadorRepository repo;

    public CoordenadorService(CoordenadorRepository repo){
        this.repo = repo;
    }

    @Override
    public Page<Coordenador> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Coordenador get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Coordenador save(Coordenador objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
