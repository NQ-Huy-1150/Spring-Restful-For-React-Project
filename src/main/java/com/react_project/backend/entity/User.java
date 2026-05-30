package com.react_project.backend.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String fullName;
    String email;
    String phoneNumber;
    String password;
    boolean isAdmin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // user one-to-many todolist
    private List<TodoList> todoLists;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<HousingExpense> housingExpenses;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<PersionalExpenses> persionalExpenses;

}
