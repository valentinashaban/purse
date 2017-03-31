package com.endava.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by vsaban on 3/15/2017.
 */
@Data
@Entity(name = "wherefroms")
public class Wherefrom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wherefrom")
    private Long id;

    @NotNull
    private String name;
}
