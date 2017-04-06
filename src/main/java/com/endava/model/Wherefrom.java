package com.endava.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by vsaban on 3/15/2017.
 */
@Data
@Entity(name = "wherefroms")
public class Wherefrom {
    @Id
    @SequenceGenerator(name="wherefroms_id_seq", allocationSize = 1, sequenceName = "wherefroms_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="wherefroms_id_seq")
    @Column(name = "id_wherefrom")
    private Long id;

    @NotEmpty
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
