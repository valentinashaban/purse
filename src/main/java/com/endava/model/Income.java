package com.endava.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by vsaban on 3/15/2017.
 */
@Data
@Entity(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_income")
    private Long id;

    @NotNull
    @Min(0)
    @Column
    private Double amount;

    @Size(max = 500)
    @Column
    private String description;

    @NotNull
    @Past
    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_wherefrom")
    private Wherefrom wherefrom;
}
