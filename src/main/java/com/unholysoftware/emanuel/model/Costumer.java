package com.unholysoftware.emanuel.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "costumer")
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "generation")
    private String generation;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "costumer_group",
            joinColumns = @JoinColumn(name = "costumer_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    Set<Group> groups;

    @OneToMany(mappedBy = "costumer")
    private Set<Contract> contracts;

    public Costumer(){}

    public Costumer(long id, String name, String middleName, String lastName, String address, String generation, String phone, String email, Set<Group> groups) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.generation = generation;
        this.phone = phone;
        this.email = email;
        this.groups = groups;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", generation='" + generation + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", groups=" + groups +
                '}';
    }
}
