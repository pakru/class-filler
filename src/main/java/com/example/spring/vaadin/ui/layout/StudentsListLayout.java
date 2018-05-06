package com.example.spring.vaadin.ui.layout;

import com.example.spring.vaadin.entities.Student;
import com.example.spring.vaadin.properties.Height;
import com.example.spring.vaadin.properties.Sex;
import com.example.spring.vaadin.properties.VisionAbility;
import com.example.spring.vaadin.repo.StudentRepository;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class StudentsListLayout extends VerticalLayout {
    private final Logger logger = Logger.getLogger(this.getClass());
    private List<Student> studentList;

    @Autowired
    StudentRepository repo;


    @PostConstruct
    public void init(){
        Grid<Student> mainGrid = new Grid<>(Student.class);
        studentList = repo.findAll();
        mainGrid.setWidth("100%");
        setDefaultComponentAlignment(Alignment.TOP_CENTER);

        logger.debug("Grid columns: ");
        mainGrid.getColumns().forEach(c -> logger.debug("ID: " + c.getId() + " : " + c.getCaption()));
        mainGrid.setColumnOrder("lastName", "firstName", "sex", "heightProperty", "visionAbility");

//        Binder<Student> gridBinder = new Binder<>(Student.class);
//        gridBinder.bindInstanceFields(mainGrid.getColumns());

        ListDataProvider<Student> studentDataProvider = new ListDataProvider<>(studentList);
        mainGrid.setDataProvider(studentDataProvider);


        Button testBtn = new Button("Click to add");
        testBtn.addClickListener(c -> {
           logger.debug("Button clicked");
           Student addStudent = new Student();
           addStudent.setFirstName("Petya");
           addStudent.setLastName("Vasechkin");
           addStudent.setSex(Sex.MALE);
           addStudent.setHeightProperty(Height.MIDDLE);
           addStudent.setVisionAbility(VisionAbility.OK);
           repo.save(addStudent);
           studentDataProvider.getItems().add(addStudent);
//           studentList = repo.findAll();
           studentDataProvider.refreshAll();
           logger.debug("List of students: " + studentList);
        });


//        mainGrid.addColumn(Student::getFirstName).setId("firstnameId").setCaption("First Name");
//        mainGrid.addColumn(Student::getLastName).setId("lastnameId").setCaption("Last Name");
        addComponent(testBtn);
        addComponent(mainGrid);
    }

}
