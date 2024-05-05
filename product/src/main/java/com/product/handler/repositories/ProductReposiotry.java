package com.product.handler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.handler.entities.Product;

public interface ProductReposiotry extends JpaRepository<Product, Long> {

}
