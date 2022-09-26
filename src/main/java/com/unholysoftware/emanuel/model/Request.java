package com.unholysoftware.emanuel.model;

import javax.persistence.*;

@Entity
@Table(name = "request")
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

    public Request() {
    }

    public Request(long id, String bundle, String model, String baseColor, String plaqueColor, String plaqueModel, String picture, String textNumber, String dedication, String attachment, float cost, Contract contract) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(String baseColor) {
        this.baseColor = baseColor;
    }

    public String getPlaqueColor() {
        return plaqueColor;
    }

    public void setPlaqueColor(String plaqueColor) {
        this.plaqueColor = plaqueColor;
    }

    public String getPlaqueModel() {
        return plaqueModel;
    }

    public void setPlaqueModel(String plaqueModel) {
        this.plaqueModel = plaqueModel;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(String textNumber) {
        this.textNumber = textNumber;
    }

    public String getDedication() {
        return dedication;
    }

    public void setDedication(String dedication) {
        this.dedication = dedication;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
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
