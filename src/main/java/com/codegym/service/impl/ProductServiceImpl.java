package com.codegym.service.impl;

import com.codegym.dto.UserDto;
import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.repository.ProductRepository;
import com.codegym.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                           ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
//
//    @Override
//    public Iterable<Product> getAllProducts() {
//        return productRepository.findAll();
//    }

    @Override
    public Product save(Product product) {
        if(Objects.nonNull(product)){
          return   productRepository.save(product);
        }
        return null;
    }
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

//    @Override
//    public Product getById(Long userId) {
//        Product product = productRepository.findById(userId).get();
//       return product;
//    }

    @Override
    public ProductForm getById(Long userId) {
        Product product = productRepository.findById(userId).get();
        return modelMapper.map(product, ProductForm.class);
    }

    @Override
    public void deleteById(Long id) {
        if(Objects.nonNull(id)){
           productRepository.deleteById(id);
        }
    }

    @Query(nativeQuery = true, value = "select * " +
            "from product p " +
            "where p.name like (:name);")
    @Override
    public Optional<List<Product>> findProductByName(String name) {
        String likeName = "%" + name + "%";
        return productRepository.findProductByName(likeName);
    }

    public List<Product> findProductsWithSorting(String field){
        return  productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Override
    public Page<Product> findProductsWithPagination(int offset, int pageSize){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }
}
