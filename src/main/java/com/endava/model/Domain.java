package com.endava.model;

import lombok.Data;

import javax.persistence.*;

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

    @Column
    private String domain;
}
