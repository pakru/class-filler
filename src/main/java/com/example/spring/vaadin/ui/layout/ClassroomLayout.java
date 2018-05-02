package com.example.spring.vaadin.ui.layout;

import com.example.spring.vaadin.entities.ClassRoom;
import com.example.spring.vaadin.repo.StudentRepository;
import com.example.spring.vaadin.ui.DeskUI;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@SpringComponent
@UIScope
public class ClassroomLayout extends VerticalLayout {
    Logger logger = Logger.getLogger(this.getClass());
    GridLayout desksLayout = new GridLayout(3,5);

    @Autowired
    ClassRoom classRoom;

    @Autowired
    StudentRepository repo;

//    @Autowired
//    DeskUI desk1;
//    DeskUI[][] desksUI = new DeskUI[][]{{new }};

    public ClassroomLayout() {
//        super(3,5);


//        logger.debug("         ------ ClassroomLayout");

//        logger.debug("         ------ ClassroomLayout: constructor end");
//        addComponent(desk);
    }

    @PostConstruct
    public void init(){
//        setDefaultComponentAlignment(Alignment.TOP_CENTER);

        Button accomodateBtn = new Button("Accomodate");
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
        setWidth("100%");

        addComponent(accomodateBtn);
        accomodateBtn.addClickListener(e -> {
            classRoom.clearClass();
            classRoom.accomodateAsOrdered(repo.findAll());
            renderDesks();}); // добавить


//        desksLayout.addComponent(new DeskUI());
//        desksLayout.addComponent(new DeskUI());
//        desksLayout.addComponent(new DeskUI());
//        desksLayout.addComponent(new DeskUI());
//        desksLayout.addComponent(new DeskUI());
//        desksLayout.addComponent(new DeskUI());
        renderDesks();

        addComponent(desksLayout);
    }

    private void renderDesks(){
        logger.debug("Rendering Desks");
        desksLayout.removeAllComponents();
        classRoom.getDesksAsList().forEach(d -> desksLayout.addComponent(new DeskUI(d))); // дописать!
    }
}
