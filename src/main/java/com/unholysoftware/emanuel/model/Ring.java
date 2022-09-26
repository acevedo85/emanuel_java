package com.unholysoftware.emanuel.model;

import javax.persistence.*;

@Entity
@Table(name = "rings")
public class Ring {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "model")
    private String model;

    @Column(name = "weight")
    private float weight;

    @Column(name = "size")
    private String size;

    @Column(name = "cost")
    private float cost;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    public Ring() {
    }

    public Ring(long id, String type, String model, float weight, String size, float cost, Contract contract) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.weight = weight;
        this.size = size;
        this.cost = cost;
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "Ring{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", weight=" + weight +
                ", size='" + size + '\'' +
                ", cost=" + cost +
                ", contract=" + contract +
                '}';
    }
}
