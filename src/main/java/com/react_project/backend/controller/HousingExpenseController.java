package com.react_project.backend.controller;

import com.react_project.backend.dto.request.HousingExpenseRequest;
import com.react_project.backend.dto.response.ApiResponse;
import com.react_project.backend.dto.response.HousingExpenseResponse;
import com.react_project.backend.service.HousingExpenseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/housings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HousingExpenseController {

    HousingExpenseService housingExpenseService;

    @PostMapping()
    public ApiResponse<HousingExpenseResponse> createHe(@RequestBody HousingExpenseRequest housingExpenseRequest){
        return ApiResponse.<HousingExpenseResponse>builder()
                .result(housingExpenseService.createHE(housingExpenseRequest))
                .build();
    }

    @GetMapping()
    public ApiResponse<List<HousingExpenseResponse>> getAllHe(){
        return ApiResponse.<List<HousingExpenseResponse>>builder()
                .result(housingExpenseService.getAllHe())
                .build();
    }

    @DeleteMapping("{id}")
    public ApiResponse<String> deleteHe(@PathVariable String id){
        return ApiResponse.<String>builder()
                .result("Delete successfully")
                .build();
    }

    @PutMapping("{id}")
    public ApiResponse<HousingExpenseResponse> updateHe(@PathVariable String id, @RequestBody HousingExpenseRequest housingExpenseRequest){
        return ApiResponse.<HousingExpenseResponse>builder()
                .result(housingExpenseService.updateHe(id,housingExpenseRequest))
                .build();
    }

}
