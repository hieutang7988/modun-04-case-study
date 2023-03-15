package com.codegym.service;
import com.codegym.model.Category;

import java.util.Optional;

public interface CategoryService {

    Iterable<Category> findAll();

    Optional<Category>  findById(Long id);

    Category save(Category category);

    void deleteById(Long id);
}
