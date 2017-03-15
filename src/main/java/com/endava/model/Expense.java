package com.endava.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vsaban on 3/15/2017.
 */
@Data
@Entity(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expense")
    private Long id;

    @Column
    private Double amount;

    @Column
    private String description;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_domain")
    private Domain domain;
}
