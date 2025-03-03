package com.gklyphon.sabor_digital.table.application.services;

public interface IService <T, N>{
    T findById(Long id);
    T save(N n);
    T update(Long id, N n);
    void deleteById(Long id);
}
