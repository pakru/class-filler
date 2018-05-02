package com.example.spring.vaadin.entities;

public class Seat {
    private int cost;
    private Student student;
    private int var;

    public Seat(int cost) {
        this.cost = cost;
        student = null;
    }

    public Seat(int cost, int var) {
        this.cost = cost;
        this.var = var;
        student = null;
    }

    /**
     * свободное ли данное место
     */
    public boolean isFree() {
        if (student == null)
            return true;
        else
            return false;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }
}
