package com.react_project.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react_project.backend.entity.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
    boolean existsById(int id);

    boolean existsByIdAndUser_Id(int id, int userId);

    List<Catalog> findAllByUser_Id(int userId);

    Optional<Catalog> findByIdAndUser_Id(int id, int userId);
}
