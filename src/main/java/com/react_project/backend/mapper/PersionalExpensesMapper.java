package com.react_project.backend.mapper;

import com.react_project.backend.dto.request.PersionalExpensesRequest;
import com.react_project.backend.dto.response.PersionalExpensesResponse;
import com.react_project.backend.entity.PersionalExpenses;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersionalExpensesMapper {
    PersionalExpenses toPe(PersionalExpensesRequest persionalExpensesRequest);

    PersionalExpensesResponse toPeResponse(PersionalExpenses persionalExpenses);
}
