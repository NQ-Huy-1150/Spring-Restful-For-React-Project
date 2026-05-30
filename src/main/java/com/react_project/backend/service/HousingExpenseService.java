package com.react_project.backend.service;

import com.react_project.backend.domain.HousingExpense;
import com.react_project.backend.dto.request.HousingExpenseRequest;
import com.react_project.backend.dto.response.HousingExpenseResponse;
import com.react_project.backend.mapper.HousingExpenseMapper;
import com.react_project.backend.repository.HousingExpenseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HousingExpenseService {

    HousingExpenseRepository housingExpenseRepository;
    HousingExpenseMapper housingExpenseMapper;

    public HousingExpenseResponse createHE(HousingExpenseRequest housingExpenseRequest){
        HousingExpense housingExpense = housingExpenseMapper.toHe(housingExpenseRequest);
        return housingExpenseMapper.toHeResponse(housingExpenseRepository.save(housingExpense));
    }

    public List<HousingExpenseResponse> getAllHe(){
        return housingExpenseRepository.findAll().stream().map(housingExpenseMapper ::toHeResponse).toList();
    }

    public HousingExpenseResponse findHeById(String idHe){
        HousingExpense he = housingExpenseRepository.findById(idHe).orElseThrow(() -> new NullPointerException("don't find"));

        return housingExpenseMapper.toHeResponse(he);
    }

    public void deleteHe(String idHe){
        housingExpenseRepository.deleteById(idHe);
    }

    public HousingExpenseResponse updateHe(String idHe,HousingExpenseRequest housingExpenseRequest){
        HousingExpense housingExpense = housingExpenseRepository.findById(idHe).orElseThrow(() -> new NullPointerException("don't find"));
        housingExpense = housingExpenseMapper.toHe(housingExpenseRequest);
        housingExpenseRepository.save(housingExpense);
        return housingExpenseMapper.toHeResponse(housingExpense);
    }

}
