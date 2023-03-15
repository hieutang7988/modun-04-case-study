package com.codegym.service;

import com.codegym.model.Category;
import com.codegym.model.Producer;

import java.util.Optional;

public interface ProducerService {
    Iterable<Producer> findAll();

    Optional<Producer> findById(Long id);

    Producer save(Producer producer);

    void deleteById(Long id);
}
