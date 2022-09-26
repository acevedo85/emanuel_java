package com.unholysoftware.emanuel.model;

import javax.persistence.*;

@Entity
@Table(name = "bundle")
public class Bundle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "second")
    private boolean wantsSecond;

    @Column(name = "cost")
    private float cost;
}
