package com.unholysoftware.emanuel.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "group")
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

    @ManyToMany(mappedBy = "costumer_group")
    Set<Costumer> costumers;

    public Group() {
    }

    public Group(long id, String school, String career, String name) {
        this.id = id;
        this.school = school;
        this.career = career;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
