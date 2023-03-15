package com.codegym.service.impl;
import com.codegym.model.Producer;
import com.codegym.repository.ProducerRepository;
import com.codegym.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private ProducerRepository producerRepository;


    @Override
    public Iterable<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    public void deleteById( Long id ) {
        producerRepository.deleteById(id);
    }
}
