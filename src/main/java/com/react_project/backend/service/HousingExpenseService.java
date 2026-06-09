package com.react_project.backend.service;

import com.react_project.backend.entity.HousingExpense;
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

    public HousingExpenseResponse createHE(HousingExpenseRequest request){

        HousingExpense housingExpense = housingExpenseMapper.toHe(request);
        calculateBills(housingExpense);

        housingExpenseRepository.save(housingExpense);
        return housingExpenseMapper.toHeResponse(housingExpense);
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

    public HousingExpenseResponse updateHe(String idHe,HousingExpenseRequest request){
        HousingExpense housingExpense = housingExpenseRepository.findById(idHe).orElseThrow(() -> new NullPointerException("don't find"));

        housingExpense.setMonth(request.getMonth());
        housingExpense.setHousePrice(request.getHousePrice());
        housingExpense.setAmoutOfElectric(request.getAmoutOfElectric());
        housingExpense.setElectricityPrice(request.getElectricityPrice());
        housingExpense.setAmoutOfWater(request.getAmoutOfWater());
        housingExpense.setWaterPrice(request.getWaterPrice());
        housingExpense.setServiceCosts(request.getServiceCosts());
        housingExpense.setOthercosts(request.getOthercosts());
        calculateBills(housingExpense);

        housingExpenseRepository.save(housingExpense);
        return housingExpenseMapper.toHeResponse(housingExpense);
    }

    private void calculateBills(HousingExpense housingExpense) {
        double electricityBill = toDouble(housingExpense.getAmoutOfElectric())
                * toDouble(housingExpense.getElectricityPrice());
        double waterBill = toDouble(housingExpense.getAmoutOfWater())
                * toDouble(housingExpense.getWaterPrice());

        housingExpense.setElectricityBill(electricityBill);
        housingExpense.setWaterBill(waterBill);
        housingExpense.setTotal(
                toDouble(housingExpense.getHousePrice())
                        + electricityBill
                        + waterBill
                        + toDouble(housingExpense.getOthercosts())
                        + toDouble(housingExpense.getServiceCosts()));
    }

    private double toDouble(Double value) {
        return value == null ? 0 : value;
    }

}
