package com.react_project.backend.repository;

import com.react_project.backend.entity.PersionalExpenses;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersionalExpensesRepository extends JpaRepository<PersionalExpenses,String> {
    List<PersionalExpenses> findAllByUser_Id(int userId);

    Optional<PersionalExpenses> findByIdAndUser_Id(String id, int userId);
}
