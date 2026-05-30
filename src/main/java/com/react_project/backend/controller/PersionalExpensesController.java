package com.react_project.backend.controller;

import com.react_project.backend.dto.request.HousingExpenseRequest;
import com.react_project.backend.dto.request.PersionalExpensesRequest;
import com.react_project.backend.dto.response.HousingExpenseResponse;
import com.react_project.backend.dto.response.PersionalExpensesResponse;
import com.react_project.backend.service.HousingExpenseService;
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
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class PersionalExpensesController {

    PersionalExpensesService persionalExpensesService;

    @PostMapping()
    public ResponseEntity<PersionalExpensesResponse> createPe(@RequestBody PersionalExpensesRequest persionalExpensesRequest){
        return ResponseEntity.ok().body(persionalExpensesService.createPe(persionalExpensesRequest));
    }

    @GetMapping()
    public ResponseEntity<List<PersionalExpensesResponse>> getAllPe(){
        return ResponseEntity.ok().body(persionalExpensesService.getAllPe());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePe(@PathVariable String id){
        persionalExpensesService.deletePe(id);
        return ResponseEntity.ok().body("Delete successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<PersionalExpensesResponse> updatePe(@PathVariable String id, @RequestBody PersionalExpensesRequest persionalExpensesRequest){
        return ResponseEntity.ok().body(persionalExpensesService.updatePe(id,persionalExpensesRequest));
    }

}
