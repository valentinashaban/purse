package com.endava.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by vsaban on 3/15/2017.
 */
@Data
@Entity(name = "domains")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domain")
    private Long id;

    @NotNull
    private String name;
}
