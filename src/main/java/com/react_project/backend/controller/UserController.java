package com.react_project.backend.controller;

import com.react_project.backend.dto.request.HousingExpenseRequest;
import com.react_project.backend.dto.request.UserRequest;
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
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok().body(userService.createUser(userRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("Delete successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable int id, @RequestBody UserRequest userRequest){
        return ResponseEntity.ok().body(userService.updateUser(id,userRequest));
    }

        @GetMapping("{id}")
    public ResponseEntity<UserResponse> getMyInfor(@PathVariable int id){
        return ResponseEntity.ok().body(userService.getMyInfor(id));
    }

}
