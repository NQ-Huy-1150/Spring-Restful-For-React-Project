package com.react_project.backend.controller;

import com.react_project.backend.dto.request.PersionalExpensesRequest;
import com.react_project.backend.dto.response.ApiResponse;
import com.react_project.backend.dto.response.PersionalExpensesResponse;
import com.react_project.backend.service.PersionalExpensesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/persionalexpensives")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class PersionalExpensesController {

    PersionalExpensesService persionalExpensesService;

    @PostMapping()
    ApiResponse<PersionalExpensesResponse> createPe(@RequestBody PersionalExpensesRequest request){
        return ApiResponse.<PersionalExpensesResponse>builder()
                .result(persionalExpensesService.createPe(request))
                .build();
    }

    @GetMapping()
    ApiResponse<List<PersionalExpensesResponse>> getAllPe(){
        return ApiResponse.<List<PersionalExpensesResponse>>builder()
                .result(persionalExpensesService.getAllPe())
                .build();
    }

    @DeleteMapping("{id}")
    ApiResponse<String> deletePe(@PathVariable String id){
        persionalExpensesService.deletePe(id);
        return ApiResponse.<String>builder()
                .result("DeleteSuccessfully")
                .build();
    }


    @PutMapping("{id}")
    ApiResponse<PersionalExpensesResponse> updatePe(@PathVariable String id,
                @RequestBody PersionalExpensesRequest PersionalExpensesRequest){
        return ApiResponse.<PersionalExpensesResponse>builder()
                .result(persionalExpensesService.updatePe(id, PersionalExpensesRequest))
                .build();
    }

}
