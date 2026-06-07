package com.react_project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react_project.backend.entity.Note;

@Repository
public interface NotePadRepository extends JpaRepository<Note, Integer> {

}