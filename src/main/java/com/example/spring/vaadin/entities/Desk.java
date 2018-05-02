package com.example.spring.vaadin.entities;

import org.apache.log4j.Logger;

public class Desk {
    private final Logger logger = Logger.getLogger(this.getClass());

    private Seat var1Seat, var2Seat;

    public Desk(int var1Cost, int var2Cost){
        var1Seat = new Seat(var1Cost, 1);
        var2Seat = new Seat(var2Cost, 2);
    }

    /***
     * @return null - if no free place on desk*
     */
    public Seat getFirstFreeSeat() {
        if (var1Seat.isFree()){
            logger.debug("Seat var 1 of Desk is Free");
            return var1Seat;
        }
        if (var2Seat.isFree()){
            logger.debug("Seat var 2 of Desk is Free");
            return var2Seat;
        }
        return null;
    }

    public Seat getVar1Seat() {
        return var1Seat;
    }

    public void setVar1Seat(Seat var1Seat) {
        this.var1Seat = var1Seat;
    }

    public Seat getVar2Seat() {
        return var2Seat;
    }

    public void setVar2Seat(Seat var2Seat) {
        this.var2Seat = var2Seat;
    }

    public String getVar1StudentFullName(){
        if (var1Seat.getStudent() == null)
            return null;
        return var1Seat.getStudent().getLastName() + " " + var1Seat.getStudent().getFirstName();
    }

    public String getVar2StudentFullName(){
        if (var2Seat.getStudent() == null)
            return null;
        return var2Seat.getStudent().getLastName() + " " + var2Seat.getStudent().getFirstName();
    }

}
