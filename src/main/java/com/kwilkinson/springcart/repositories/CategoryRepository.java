package com.kwilkinson.springcart.repositories;

import com.kwilkinson.springcart.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
