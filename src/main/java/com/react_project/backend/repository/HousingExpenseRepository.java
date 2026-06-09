package com.react_project.backend.repository;

import com.react_project.backend.entity.HousingExpense;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingExpenseRepository extends JpaRepository<HousingExpense,String> {
    List<HousingExpense> findAllByUser_Id(int userId);

    Optional<HousingExpense> findByIdAndUser_Id(String id, int userId);
}
