package com.test.task.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "s_gtype")
public class SGType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

   /* @OneToMany (mappedBy = "type")
    private Set<SGood> goodSet;*/
}
