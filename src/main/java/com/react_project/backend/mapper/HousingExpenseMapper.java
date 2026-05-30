package com.react_project.backend.mapper;

import com.react_project.backend.domain.HousingExpense;
import com.react_project.backend.dto.request.HousingExpenseRequest;
import com.react_project.backend.dto.response.HousingExpenseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HousingExpenseMapper {
    HousingExpense toHe(HousingExpenseRequest housingExpenseRequest);
    HousingExpenseResponse toHeResponse(HousingExpense housingExpense);
}
