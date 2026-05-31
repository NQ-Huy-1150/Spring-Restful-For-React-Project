package com.react_project.backend.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    String username;
    String password;

    String fullName;
    String email;
    String phoneNumber;

    boolean isAdmin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // user one-to-many todolist
    private List<TodoList> todoLists;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<HousingExpense> housingExpenses;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<PersionalExpenses> persionalExpenses;

}
