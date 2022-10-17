package com.unholysoftware.emanuel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "requests")
@Getter @Setter @NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "bundle")
    private String bundle;

    @Column(name = "model")
    private String model;

    @Column(name = "base_color")
    private String baseColor;

    @Column(name = "plaque_color")
    private String plaqueColor;

    @Column(name = "plaque_model")
    private String plaqueModel;

    @Column(name = "picture")
    private String picture;

    @Column(name = "text_number")
    private String textNumber;

    @Column(name = "dedication")
    private String dedication;

    @Column(name = "attachment")
    private String attachment;

    @Column(name = "cost")
    private float cost;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    public Request(String bundle, String model, String baseColor, String plaqueColor, String plaqueModel, String picture, String textNumber, String dedication, String attachment, float cost, Contract contract) {
        this.bundle = bundle;
        this.model = model;
        this.baseColor = baseColor;
        this.plaqueColor = plaqueColor;
        this.plaqueModel = plaqueModel;
        this.picture = picture;
        this.textNumber = textNumber;
        this.dedication = dedication;
        this.attachment = attachment;
        this.cost = cost;
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", bundle='" + bundle + '\'' +
                ", model='" + model + '\'' +
                ", baseColor='" + baseColor + '\'' +
                ", plaqueColor='" + plaqueColor + '\'' +
                ", plaqueModel='" + plaqueModel + '\'' +
                ", picture='" + picture + '\'' +
                ", textNumber='" + textNumber + '\'' +
                ", dedication='" + dedication + '\'' +
                ", attachment='" + attachment + '\'' +
                ", cost=" + cost +
                ", contract=" + contract +
                '}';
    }
}
