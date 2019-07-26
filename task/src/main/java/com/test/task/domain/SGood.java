package com.test.task.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "s_good")
public class SGood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "type",
            nullable = false
    )
    private SGType sgType;
}
