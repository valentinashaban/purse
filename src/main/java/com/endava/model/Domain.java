package com.endava.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by vsaban on 3/15/2017.
 */
@Data
@Entity
@Table(name = "domains")
public class Domain {

    @Id
    @SequenceGenerator(name="domains_id_seq", allocationSize = 1, sequenceName = "domains_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="domains_id_seq")
    @Column(name = "id_domain")
    private Long id;

    @NotEmpty(message = "Should not be empty")
    private String name;
}
