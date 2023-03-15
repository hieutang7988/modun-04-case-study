package com.codegym.repository;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(nativeQuery = true, value = "select * " +
            "from product p " +
            "where p.name like (:name);")
    Optional<List<Product>> findProductByName(@Param("name") String name);

}
