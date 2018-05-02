package com.example.spring.vaadin.ui;

import com.example.spring.vaadin.entities.Desk;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

@SpringComponent
@UIScope
public class DeskUI extends Panel {
    private final String EMPTY_CAPTION = "Empty";
    private Label seat1Caption = new Label();
    private Label seat2Caption = new Label();

    private Desk desk;

    public DeskUI(Desk desk) {
        VerticalLayout deskLayout = new VerticalLayout();
        deskLayout.setHeight("100%");
        deskLayout.setWidth("100%");
//        deskLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setDesk(desk);
        deskLayout.addComponent(seat1Caption);
        seat1Caption.setWidth(null);
        deskLayout.setComponentAlignment(seat1Caption,Alignment.TOP_LEFT);
        deskLayout.addComponent(seat2Caption);
        seat2Caption.setWidth(null);
        deskLayout.setComponentAlignment(seat2Caption,Alignment.BOTTOM_RIGHT);

        setWidth("230px");
        setHeight("70px");
        setContent(deskLayout);
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
        if (this.desk.getVar1StudentFullName() == null){
            seat1Caption.setCaption("Var1: " + EMPTY_CAPTION);
        } else
            seat1Caption.setCaption(this.desk.getVar1StudentFullName());

        if (this.desk.getVar2StudentFullName() == null){
            seat2Caption.setCaption("Var2: " + EMPTY_CAPTION);
        } else
            seat2Caption.setCaption(this.desk.getVar2StudentFullName());
    }

}
