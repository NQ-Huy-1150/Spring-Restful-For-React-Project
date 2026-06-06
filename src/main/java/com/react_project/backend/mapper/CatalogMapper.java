package com.react_project.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.react_project.backend.dto.response.CatalogResponse;
import com.react_project.backend.entity.Catalog;

@Mapper(componentModel = "spring")
public interface CatalogMapper {
    CatalogResponse toResponse(Catalog cata);
}
