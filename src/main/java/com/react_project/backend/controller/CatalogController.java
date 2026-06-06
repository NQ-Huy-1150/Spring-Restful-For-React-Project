package com.react_project.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.react_project.backend.dto.request.CatalogDTO;
import com.react_project.backend.dto.request.CatalogUpdateDTO;
import com.react_project.backend.dto.response.MessageResponse;
import com.react_project.backend.service.CatalogService;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/fetch-all-catalog")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getAllCatalog() {
        return ResponseEntity.ok(this.catalogService.fetchAllCatalogResponses());
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> deleteCatalog(@PathVariable int id) {
        if (this.catalogService.deleteCatalogById(id)) {
            return ResponseEntity.ok(new MessageResponse("Deleted successfully!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("id not found"));
    }

    @PostMapping("/create-catalog")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getCreateCatalog(@RequestBody CatalogDTO dto) {
        return ResponseEntity.ok(this.catalogService.createCatalog(dto));
    }

    @PostMapping("/update-catalog")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> getupdateCatalog(@RequestBody CatalogUpdateDTO dto) {
        this.catalogService.updateCatalog(dto);
        return ResponseEntity.ok(new MessageResponse("update successfully!"));
    }
}
