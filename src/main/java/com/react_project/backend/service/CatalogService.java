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

    public CatalogService(CatalogRepository catalogRepository, CatalogMapper mapper) {
        this.catalogRepository = catalogRepository;
        this.mapper = mapper;
    }

    public List<Catalog> fetchAllCatalog() {
        return this.catalogRepository.findAll();
    }

    public List<CatalogResponse> fetchAllCatalogResponses() {
        return this.catalogRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Optional<Catalog> getCatalogById(int id) {
        return this.catalogRepository.findById(id);
    }

    public boolean isCatalogExisted(int id) {
        return this.catalogRepository.existsById(id);
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
        cata.setTitle(dto.getTitle());
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
