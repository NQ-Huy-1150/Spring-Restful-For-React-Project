package com.react_project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react_project.backend.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
