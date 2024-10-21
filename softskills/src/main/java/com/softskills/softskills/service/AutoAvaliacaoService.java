package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.AutoAvaliacao;
import com.softskills.softskills.repository.AutoAvaliacaoRepository;

@Service
public class AutoAvaliacaoService implements Iservice<AutoAvaliacao>{
    
    private AutoAvaliacaoRepository repo;

    public AutoAvaliacaoService(AutoAvaliacaoRepository repo){
        this.repo = repo;
    }

    @Override
    public Page<AutoAvaliacao> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public AutoAvaliacao get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public AutoAvaliacao save(AutoAvaliacao objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
