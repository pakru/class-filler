package com.example.spring.vaadin.ui.layout;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class MainLayout extends VerticalLayout {
    private final Label headerLabel = new Label();
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ClassroomLayout classroomLayout;

    @Autowired
    private StudentsListLayout studentsListLayout;

    public MainLayout() {
        logger.debug("         -----   Main Layout");
    }

    @PostConstruct
    public void init(){
        headerLabel.setValue("Hello!");
        headerLabel.setWidth(null);
        setDefaultComponentAlignment(Alignment.TOP_CENTER);
        addComponent(headerLabel);
//        setComponentAlignment(headerLabel,Alignment.TOP_CENTER);
        TabSheet tabs = new TabSheet();

        VerticalLayout studentsTab = new VerticalLayout();
        studentsTab.setCaption("Students");
        studentsTab.setWidth("100%");
        studentsTab.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        studentsTab.addComponent(studentsListLayout);

        VerticalLayout classroomTab = new VerticalLayout();
        classroomTab.setCaption("Classroom");
        classroomTab.setWidth("100%");
        classroomTab.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        logger.debug("Classroom layout object: " + classroomLayout );
        classroomTab.addComponent(classroomLayout);

        tabs.addTab(studentsTab);
        tabs.addTab(classroomTab);

        tabs.setWidth("90%");
        addComponent(tabs);
    }

}
