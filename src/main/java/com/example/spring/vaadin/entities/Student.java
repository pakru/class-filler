package com.example.spring.vaadin.entities;

import com.example.spring.vaadin.properties.Height;
import com.example.spring.vaadin.properties.Sex;
import com.example.spring.vaadin.properties.VisionAbility;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "height", nullable = false)
    private Height heightProperty;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    @Column(name = "vision", nullable = false)
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
