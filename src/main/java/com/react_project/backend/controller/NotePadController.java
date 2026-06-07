package com.react_project.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.react_project.backend.dto.request.NotePadDTO;
import com.react_project.backend.dto.response.MessageResponse;
import com.react_project.backend.dto.response.NotePadResponse;
import com.react_project.backend.service.NotePadService;

@RestController
@RequestMapping("/api/v1/note")
public class NotePadController {
    private final NotePadService notePadService;

    public NotePadController(NotePadService notePadService) {
        this.notePadService = notePadService;
    }

    @GetMapping("/get-all-notes")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getAllNote() {
        return ResponseEntity.ok(this.notePadService.fetchAllNotes());
    }

    @PostMapping("/create-notepad")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getCreateNote(@RequestBody NotePadDTO dto) {
        NotePadResponse response = this.notePadService.createNote(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-notepad")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getUpdateNote(@RequestBody NotePadDTO dto) {
        if (this.notePadService.existedById(dto.getId())) {
            this.notePadService.updateNotePad(dto);
            return ResponseEntity.ok(new MessageResponse("Updated successfully !"));
        }
        return ResponseEntity.badRequest().body("Note id not found !");
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getDeleteNote(@PathVariable int id) {
        if (this.notePadService.deleteNotePad(id)) {
            return ResponseEntity.ok(new MessageResponse("Deleted successfully !"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Note id not found !"));
    }
}
