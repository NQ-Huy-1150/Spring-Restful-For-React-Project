package com.react_project.backend.controller;

import com.react_project.backend.dto.request.PersionalExpensesRequest;
import com.react_project.backend.dto.response.ApiResponse;
import com.react_project.backend.dto.response.PersionalExpensesResponse;
import com.react_project.backend.service.PersionalExpensesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@Slf4j
@RestController
@Validated
@RequestMapping("/persionalexpensives")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class PersionalExpensesController {

    PersionalExpensesService persionalExpensesService;

    @PostMapping()
    @CrossOrigin(origins = "http://localhost:5173")
    ApiResponse<PersionalExpensesResponse> createPe(@Valid @RequestBody PersionalExpensesRequest request) {
        return ApiResponse.<PersionalExpensesResponse>builder()
                .result(persionalExpensesService.createPe(request))
                .build();
    }

    @GetMapping()
    @CrossOrigin(origins = "http://localhost:5173")
    ApiResponse<List<PersionalExpensesResponse>> getAllPe() {
        return ApiResponse.<List<PersionalExpensesResponse>>builder()
                .result(persionalExpensesService.getAllPe())
                .build();
    }

    @DeleteMapping("{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    ApiResponse<String> deletePe(@PathVariable String id) {
        persionalExpensesService.deletePe(id);
        return ApiResponse.<String>builder()
                .result("DeleteSuccessfully")
                .build();
    }

    @PutMapping("{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    ApiResponse<PersionalExpensesResponse> updatePe(@PathVariable String id,
            @Valid @RequestBody PersionalExpensesRequest PersionalExpensesRequest) {
        return ApiResponse.<PersionalExpensesResponse>builder()
                .result(persionalExpensesService.updatePe(id, PersionalExpensesRequest))
                .build();
    }

}
