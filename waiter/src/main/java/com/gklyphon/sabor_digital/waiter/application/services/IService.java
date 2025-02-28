package com.gklyphon.sabor_digital.waiter.application.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService <T, N>{

    Page<T> findAll(Pageable pageable);
    T findById(Long id);
    T save(N n);
    T update(Long id, N n);
    void deleteById(Long id);
}
