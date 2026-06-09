package com.react_project.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.react_project.backend.dto.request.NotePadDTO;
import com.react_project.backend.dto.response.NotePadResponse;
import com.react_project.backend.entity.Catalog;
import com.react_project.backend.entity.Note;
import com.react_project.backend.entity.User;
import com.react_project.backend.mapper.NotePadMapper;
import com.react_project.backend.repository.NotePadRepository;

@Service
public class NotePadService {
    private final NotePadRepository notePadRepository;
    private final NotePadMapper mapper;
    private final CatalogService catalogService;
    private final CurrentUserService currentUserService;

    public NotePadService(NotePadRepository notePadRepository, NotePadMapper mapper, CatalogService catalogService,
            CurrentUserService currentUserService) {
        this.notePadRepository = notePadRepository;
        this.mapper = mapper;
        this.catalogService = catalogService;
        this.currentUserService = currentUserService;
    }

    public List<NotePadResponse> fetchAllNotes() {
        return this.notePadRepository.findAllByUser_Id(currentUserService.getCurrentUserId()).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public NotePadResponse createNote(NotePadDTO dto) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        Date time = new Date();
        note.setCreatedAt(time);
        User user = currentUserService.getCurrentUser();
        note.setUser(user);
        if (dto.getCatalogId() != null) {
            Optional<Catalog> optional = this.catalogService.getCatalogById(dto.getCatalogId());
            Catalog cata = optional.orElseThrow(
                    () -> new RuntimeException("Catalog Id not found !" + dto.getCatalogId()));
            note.setCatalog(cata);
        }
        note = this.notePadRepository.save(note);
        return this.mapper.toResponse(note);
    }

    public Optional<Note> getNoteById(int id) {
        return this.notePadRepository.findByIdAndUser_Id(id, currentUserService.getCurrentUserId());
    }

    public void updateNotePad(NotePadDTO dto) {
        Optional<Note> optional = notePadRepository.findByIdAndUser_Id(dto.getId(), currentUserService.getCurrentUserId());
        Note note = optional.orElseThrow(() -> new RuntimeException("Note Id not found !" + dto.getId()));
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        Date time = new Date();
        note.setUpdatedAt(time);
        if (dto.getCatalogId() != null
                && (note.getCatalog() != null ? note.getCatalog().getId() : null) != dto.getCatalogId()) {
            Optional<Catalog> cataOptional = this.catalogService.getCatalogById(dto.getCatalogId());
            Catalog cata = cataOptional.orElseThrow(
                    () -> new RuntimeException("Catalog Id not found !" + dto.getCatalogId()));
            note.setCatalog(cata);
        }
        this.notePadRepository.save(note);
    }

    public boolean existedById(int id) {
        return this.notePadRepository.findByIdAndUser_Id(id, currentUserService.getCurrentUserId()).isPresent();
    }

    public boolean deleteNotePad(int id) {
        Optional<Note> optional = notePadRepository.findByIdAndUser_Id(id, currentUserService.getCurrentUserId());
        if (optional.isEmpty()) {
            return false;
        }
        this.notePadRepository.delete(optional.get());
        return true;
    }

}
