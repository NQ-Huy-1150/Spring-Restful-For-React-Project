package com.react_project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react_project.backend.entity.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
    boolean existsById(int id);
}
