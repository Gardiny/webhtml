package com.softskills.softskills.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Iservice<T> {

    public Page<T> get(String termoBusca, Pageable page);
    public T get(Long id);
    public T save(T objeto);
    public void delete(Long id);
}
