package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Capacitacao;
import com.softskills.softskills.repository.CapacitacaoRepository;

@Service
public class CapacitacaoService implements Iservice<Capacitacao>{

    //Falta implementar cache

    private final CapacitacaoRepository repo;

    public CapacitacaoService(CapacitacaoRepository repo){
        this.repo = repo;
    }

    @Override
    public Page<Capacitacao> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Capacitacao get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Capacitacao save(Capacitacao objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}

