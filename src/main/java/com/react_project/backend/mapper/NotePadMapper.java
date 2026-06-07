package com.react_project.backend.mapper;

import org.mapstruct.Mapper;

import com.react_project.backend.dto.response.NotePadResponse;
import com.react_project.backend.entity.Note;

@Mapper(componentModel = "spring")
public interface NotePadMapper {
    NotePadResponse toResponse(Note note);
}
