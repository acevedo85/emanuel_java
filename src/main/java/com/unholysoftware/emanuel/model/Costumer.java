package com.unholysoftware.emanuel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "costumers")
@Getter @Setter @NoArgsConstructor
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

    public Costumer(String name, String middleName, String lastName, String address, String generation, String phone, String email, Set<Group> groups) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.generation = generation;
        this.phone = phone;
        this.email = email;
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
