package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
