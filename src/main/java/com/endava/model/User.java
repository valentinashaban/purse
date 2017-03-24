package com.endava.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsaban on 3/15/2017.
 */
@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    @Column
    private String login;

    @NotNull
    @Size(min = 6, max = 10)
    @Column
    private String password;

    @Email
    @Column
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    List<Expense> expenses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Income> incomes = new ArrayList<>();
}
