package com.react_project.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react_project.backend.domain.TodoList;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
    List<TodoList> findAll();

    Optional<TodoList> findById(int id);

    boolean existsById(int id);
}
