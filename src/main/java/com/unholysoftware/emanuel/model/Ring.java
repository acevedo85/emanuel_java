package com.unholysoftware.emanuel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rings")
@Getter @Setter @NoArgsConstructor
public class Ring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Ring(String type, String model, float weight, String size, float cost, Contract contract) {
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
