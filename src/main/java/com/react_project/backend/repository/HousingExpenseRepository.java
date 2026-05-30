package com.react_project.backend.repository;

import com.react_project.backend.domain.HousingExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingExpenseRepository extends JpaRepository<HousingExpense,String> {

}
