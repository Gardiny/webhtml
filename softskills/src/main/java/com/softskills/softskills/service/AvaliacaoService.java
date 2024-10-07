package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Avaliacao;
import com.softskills.softskills.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService implements Iservice<Avaliacao>{

    private AvaliacaoRepository repo;

    public AvaliacaoService(AvaliacaoRepository repo){
        this.repo = repo;
    }

    @Override
    public Page<Avaliacao> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Avaliacao get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Avaliacao save(Avaliacao objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
