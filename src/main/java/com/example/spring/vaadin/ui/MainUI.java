package com.example.spring.vaadin.ui;

import com.example.spring.vaadin.ui.layout.MainLayout;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class MainUI extends UI {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MainLayout mainLayout;

//    @Autowired
//    private ClassroomLayout classroomLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        showMainLayout();

    }

    private void showMainLayout(){
//        logger.debug("Main Layout: " + mainLayout);
//        logger.debug("Main Layout - classroom layout: " + classroomLayout);
        setContent(mainLayout);
    }
}
