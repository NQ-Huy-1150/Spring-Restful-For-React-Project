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
    private CatalogService catalogService;

    public NotePadService(NotePadRepository notePadRepository, NotePadMapper mapper, CatalogService catalogService) {
        this.notePadRepository = notePadRepository;
        this.mapper = mapper;
        this.catalogService = catalogService;
    }

    public List<NotePadResponse> fetchAllNotes() {
        return this.notePadRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    public NotePadResponse createNote(NotePadDTO dto) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        Date time = new Date();
        note.setCreatedAt(time);
        User user = new User();
        user.setId(1);
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
        return this.notePadRepository.findById(id);
    }

    public void updateNotePad(NotePadDTO dto) {
        Optional<Note> optional = getNoteById(dto.getId());
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
        return this.notePadRepository.existsById(id);
    }

    public boolean deleteNotePad(int id) {
        if (!existedById(id)) {
            return false;
        }
        this.notePadRepository.deleteById(id);
        return true;
    }

}
