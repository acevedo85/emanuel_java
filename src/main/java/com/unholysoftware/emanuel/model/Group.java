package com.unholysoftware.emanuel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groups")
@Setter @Getter @NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "school")
    private String school;

    @Column(name = "career")
    private String career;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "groups")
    Set<Costumer> costumers;

    public Group(String school, String career, String name) {
        this.school = school;
        this.career = career;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", school='" + school + '\'' +
                ", career='" + career + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
