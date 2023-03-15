package com.codegym.service;
import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<List<Product>> findProductByName(String name);

    Product save(Product product);

    Iterable<Product> findAll();

    ProductForm getById(Long userId);

    void deleteById(Long ids);

    List<Product> findProductsWithSorting(String field);

    Page<Product> findProductsWithPagination(int offset, int pageSize);
}
