package com.example.spring.vaadin.entities;

import com.example.spring.vaadin.properties.Height;
import com.example.spring.vaadin.properties.Sex;
import com.example.spring.vaadin.properties.VisionAbility;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "height")
    private Height heightProperty;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Enumerated(EnumType.STRING)
    @Column(name = "vision")
    private VisionAbility visionAbility;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Height getHeightProperty() {
        return heightProperty;
    }

    public void setHeightProperty(Height heightProperty) {
        this.heightProperty = heightProperty;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public VisionAbility getVisionAbility() {
        return visionAbility;
    }

    public void setVisionAbility(VisionAbility visionAbility) {
        this.visionAbility = visionAbility;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", heightProperty=" + heightProperty +
                ", sex=" + sex +
                ", visionAbility=" + visionAbility +
                '}';
    }
}
