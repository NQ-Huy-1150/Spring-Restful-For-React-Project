package com.react_project.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.react_project.backend.dto.response.NotePadResponse;
import com.react_project.backend.entity.Note;

@Mapper(componentModel = "spring")
public interface NotePadMapper {
    @Mapping(source = "catalog.id", target = "catalogId")
    NotePadResponse toResponse(Note note);
}
