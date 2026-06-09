package com.react_project.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.react_project.backend.dto.request.CatalogDTO;
import com.react_project.backend.dto.request.CatalogUpdateDTO;
import com.react_project.backend.dto.response.CatalogResponse;
import com.react_project.backend.entity.Catalog;
import com.react_project.backend.mapper.CatalogMapper;
import com.react_project.backend.repository.CatalogRepository;

@Service
public class CatalogService {
    private final CatalogRepository catalogRepository;
    private final CatalogMapper mapper;
    private final CurrentUserService currentUserService;

    public CatalogService(CatalogRepository catalogRepository, CatalogMapper mapper,
            CurrentUserService currentUserService) {
        this.catalogRepository = catalogRepository;
        this.mapper = mapper;
        this.currentUserService = currentUserService;
    }

    public List<Catalog> fetchAllCatalog() {
        return this.catalogRepository.findAllByUser_Id(currentUserService.getCurrentUserId());
    }

    public List<CatalogResponse> fetchAllCatalogResponses() {
        return this.catalogRepository.findAllByUser_Id(currentUserService.getCurrentUserId()).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Optional<Catalog> getCatalogById(int id) {
        return this.catalogRepository.findByIdAndUser_Id(id, currentUserService.getCurrentUserId());
    }

    public boolean isCatalogExisted(int id) {
        return this.catalogRepository.existsByIdAndUser_Id(id, currentUserService.getCurrentUserId());
    }

    public boolean deleteCatalogById(int id) {
        if (!isCatalogExisted(id)) {
            return false;
        }
        this.catalogRepository.deleteById(id);
        return true;
    }

    public CatalogResponse createCatalog(CatalogDTO dto) {
        Catalog cata = new Catalog();
        cata.setTitle(dto.getTitle() == null || dto.getTitle().trim().isEmpty() ? "Untitled" : dto.getTitle().trim());
        cata.setUser(currentUserService.getCurrentUser());
        cata = this.catalogRepository.save(cata);
        return mapper.toResponse(cata);
    }

    public void updateCatalog(CatalogUpdateDTO dto) {
        Optional<Catalog> optional = getCatalogById(dto.getId());
        if (optional.isPresent()) {
            Catalog cata = optional.get();
            cata.setTitle(dto.getTitle());
            cata = this.catalogRepository.save(cata);
        }
    }
}
