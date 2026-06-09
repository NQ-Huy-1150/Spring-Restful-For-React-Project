package com.react_project.backend.service;

import com.react_project.backend.dto.request.PersionalExpensesRequest;
import com.react_project.backend.dto.response.PersionalExpensesResponse;
import com.react_project.backend.entity.PersionalExpenses;
import com.react_project.backend.mapper.PersionalExpensesMapper;
import com.react_project.backend.repository.PersionalExpensesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PersionalExpensesService {

    PersionalExpensesRepository persionalExpensesRepository;
    PersionalExpensesMapper persionalExpensesMapper;
    CurrentUserService currentUserService;

    public PersionalExpensesResponse createPe(PersionalExpensesRequest request){
        PersionalExpenses persionalExpenses = persionalExpensesMapper.toPe(request);
        persionalExpenses.setUser(currentUserService.getCurrentUser());
        calculateRemainingAmount(persionalExpenses);

        persionalExpenses = persionalExpensesRepository.save(persionalExpenses);

        return persionalExpensesMapper.toPeResponse(persionalExpenses);
    }

    public List<PersionalExpensesResponse> getAllPe(){
        return persionalExpensesRepository.findAllByUser_Id(currentUserService.getCurrentUserId()).stream()
                .map(persionalExpensesMapper ::toPeResponse)
                .toList();
    }

    public PersionalExpensesResponse findPeById(String idPe){
        PersionalExpenses persionalExpensese = persionalExpensesRepository.findByIdAndUser_Id(idPe, currentUserService.getCurrentUserId())
                .orElseThrow(() -> new NullPointerException("don't find"));

        return persionalExpensesMapper.toPeResponse(persionalExpensese);
    }

    public void deletePe(String idPe){
        PersionalExpenses persionalExpenses = persionalExpensesRepository.findByIdAndUser_Id(
                idPe,
                currentUserService.getCurrentUserId()).orElseThrow(() -> new NullPointerException("don't find"));
        persionalExpensesRepository.delete(persionalExpenses);
    }

    @Transactional
    public PersionalExpensesResponse updatePe(String idPe,PersionalExpensesRequest persionalExpensesRequest){
        PersionalExpenses persionalExpenses = persionalExpensesRepository.findByIdAndUser_Id(
                idPe,
                currentUserService.getCurrentUserId()).orElseThrow(() -> new NullPointerException("don't find"));

        persionalExpenses.setMonth(persionalExpensesRequest.getMonth());
        persionalExpenses.setTotalIncome(persionalExpensesRequest.getTotalIncome());
        persionalExpenses.setHouseCost(persionalExpensesRequest.getHouseCost());
        persionalExpenses.setFoodCost(persionalExpensesRequest.getFoodCost());
        persionalExpenses.setTraveCost(persionalExpensesRequest.getTraveCost());
        persionalExpenses.setOtherCost1(persionalExpensesRequest.getOtherCost1());
        persionalExpenses.setOtherCost2(persionalExpensesRequest.getOtherCost2());
        persionalExpenses.setOtherCost3(persionalExpensesRequest.getOtherCost3());
        persionalExpenses.setSavingAndInvestment(persionalExpensesRequest.getSavingAndInvestment());
        calculateRemainingAmount(persionalExpenses);

        return persionalExpensesMapper.toPeResponse(persionalExpensesRepository.save(persionalExpenses));
    }

    private void calculateRemainingAmount(PersionalExpenses persionalExpenses) {
        persionalExpenses.setRemaningAmount(
                toDouble(persionalExpenses.getTotalIncome())
                        - toDouble(persionalExpenses.getHouseCost())
                        - toDouble(persionalExpenses.getFoodCost())
                        - toDouble(persionalExpenses.getTraveCost())
                        - toDouble(persionalExpenses.getOtherCost1())
                        - toDouble(persionalExpenses.getOtherCost2())
                        - toDouble(persionalExpenses.getOtherCost3())
                        - toDouble(persionalExpenses.getSavingAndInvestment()));
    }

    private double toDouble(Double value) {
        return value == null ? 0 : value;
    }

}
