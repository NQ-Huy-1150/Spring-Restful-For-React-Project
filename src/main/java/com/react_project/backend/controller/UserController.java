package com.react_project.backend.controller;

import com.react_project.backend.dto.request.HousingExpenseRequest;
import com.react_project.backend.dto.request.UserRequest;
import com.react_project.backend.dto.response.ApiResponse;
import com.react_project.backend.dto.response.HousingExpenseResponse;
import com.react_project.backend.dto.response.UserResponse;
import com.react_project.backend.entity.User;
import com.react_project.backend.service.HousingExpenseService;
import com.react_project.backend.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping()
    public ApiResponse<UserResponse> createUser(@RequestBody UserRequest userRequest){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(userRequest))
                .build();
    }

    @DeleteMapping("{id}")
    public ApiResponse<String> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .result("Delete successfully")
                .build();
    }

    @PutMapping("{id}")
    public ApiResponse updateUser(@PathVariable int id, @RequestBody UserRequest userRequest){
        return ApiResponse.builder()
                .result(userService.updateUser(id, userRequest))
                .build();
    }

        @GetMapping("{id}")
        public ApiResponse getMyInfor(@PathVariable int id){
        return ApiResponse.builder()
                .result(userService.getMyInfor(id))
                .build();
    }

}
