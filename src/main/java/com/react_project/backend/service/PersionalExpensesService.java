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

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PersionalExpensesService {

    PersionalExpensesRepository persionalExpensesRepository;
    PersionalExpensesMapper persionalExpensesMapper;

    public PersionalExpensesResponse createPe(PersionalExpensesRequest persionalExpensesRequest){
        PersionalExpenses persionalExpenses = persionalExpensesMapper.toPe(persionalExpensesRequest);
        return persionalExpensesMapper.toPeResponse(persionalExpensesRepository.save(persionalExpenses));
    }

    public List<PersionalExpensesResponse> getAllPe(){
        return persionalExpensesRepository.findAll().stream().map(persionalExpensesMapper ::toPeResponse).toList();
    }

    public PersionalExpensesResponse findPeById(String idPe){
        PersionalExpenses persionalExpensese = persionalExpensesRepository.findById(idPe).orElseThrow(() -> new NullPointerException("don't find"));

        return persionalExpensesMapper.toPeResponse(persionalExpensese);
    }

    public void deletePe(String idPe){
        persionalExpensesRepository.deleteById(idPe);
    }

    public PersionalExpensesResponse updatePe(String idPe,PersionalExpensesRequest persionalExpensesRequest){
        PersionalExpenses persionalExpenses = persionalExpensesRepository.findById(idPe).orElseThrow(() -> new NullPointerException("don't find"));
        persionalExpenses = persionalExpensesMapper.toPe(persionalExpensesRequest);
        persionalExpensesRepository.save(persionalExpenses);
        return persionalExpensesMapper.toPeResponse(persionalExpenses);
    }

}
