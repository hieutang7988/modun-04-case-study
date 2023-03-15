package com.codegym.repository;

import com.codegym.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer,Long> {
}
