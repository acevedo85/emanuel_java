package com.unholysoftware.emanuel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bundles")
@Getter @Setter @NoArgsConstructor
public class Bundle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "second")
    private boolean wantsSecond;

    @Column(name = "cost")
    private float cost;

    public Bundle(String name, String description, boolean wantsSecond, float cost) {
        this.name = name;
        this.description = description;
        this.wantsSecond = wantsSecond;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bundle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", wantsSecond=" + wantsSecond +
                ", cost=" + cost +
                '}';
    }
}
