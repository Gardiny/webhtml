package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softskills.softskills.model.Skill;
import com.softskills.softskills.repository.SkillRepository;


@Service
public class SkillService implements Iservice<Skill>{

//Falta implementar Cache

    private final SkillRepository repo;

    public SkillService(SkillRepository repo){
        this.repo = repo;
    }

    @Override
    public Page<Skill> get(String termoBusca, Pageable page) {
        if (termoBusca == null || termoBusca.isBlank()) {
            return repo.findAll(page);
        } else {
            return repo.busca(termoBusca,page);
        }
    }

    @Override
    public Skill get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Skill save(Skill objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
