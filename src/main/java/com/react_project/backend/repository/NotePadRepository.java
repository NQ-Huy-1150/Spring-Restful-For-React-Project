package com.react_project.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react_project.backend.entity.Note;

@Repository
public interface NotePadRepository extends JpaRepository<Note, Integer> {
    List<Note> findAllByUser_Id(int userId);

    Optional<Note> findByIdAndUser_Id(int id, int userId);
}
