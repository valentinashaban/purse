package com.endava.model;

import lombok.Data;

import javax.persistence.*;
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

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany(mappedBy = "user")
    List<Expense> expenses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Income> incomes = new ArrayList<>();
}
