package com.react_project.backend.controller;

import com.react_project.backend.dto.request.HousingExpenseRequest;
import com.react_project.backend.dto.response.HousingExpenseResponse;
import com.react_project.backend.service.HousingExpenseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<HousingExpenseResponse> createHe(@RequestBody HousingExpenseRequest housingExpenseRequest){
        return ResponseEntity.ok().body(housingExpenseService.createHE(housingExpenseRequest));
    }

    @GetMapping()
    public ResponseEntity<List<HousingExpenseResponse>> getAllHe(){
        return ResponseEntity.ok().body(housingExpenseService.getAllHe());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHe(@PathVariable String id){
        housingExpenseService.deleteHe(id);
        return ResponseEntity.ok().body("Delete successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<HousingExpenseResponse> updateHe(@PathVariable String id, @RequestBody HousingExpenseRequest housingExpenseRequest){
        return ResponseEntity.ok().body(housingExpenseService.updateHe(id,housingExpenseRequest));
    }

}
