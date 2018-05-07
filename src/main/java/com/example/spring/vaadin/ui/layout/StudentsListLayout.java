package com.example.spring.vaadin.ui.layout;

import com.example.spring.vaadin.entities.Student;
import com.example.spring.vaadin.properties.Height;
import com.example.spring.vaadin.properties.Sex;
import com.example.spring.vaadin.properties.VisionAbility;
import com.example.spring.vaadin.repo.StudentRepository;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class StudentsListLayout extends VerticalLayout {
    private final Logger logger = Logger.getLogger(this.getClass());
    private List<Student> studentList;

    private final Window studentEditDialog = new Window();

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

        initStudentEditDialog();

        ListDataProvider<Student> studentDataProvider = new ListDataProvider<>(studentList);
        mainGrid.setDataProvider(studentDataProvider);

        final Button addBtn = new Button();
        addBtn.setIcon(VaadinIcons.USER);
        addBtn.addClickListener(c -> {
           logger.debug("Button clicked");
           Student addStudent = new Student();
           addStudent.setFirstName("Petya");
           addStudent.setLastName("Vasechkin");
           addStudent.setSex(Sex.MALE);
           addStudent.setHeightProperty(Height.MIDDLE);
           addStudent.setVisionAbility(VisionAbility.OK);
           getUI().addWindow(studentEditDialog);
//           repo.save(addStudent);
//           studentDataProvider.getItems().add(addStudent);
//           studentList = repo.findAll();
           studentDataProvider.refreshAll();
           logger.debug("List of students: " + studentList);
        });


//        mainGrid.addColumn(Student::getFirstName).setId("firstnameId").setCaption("First Name");
//        mainGrid.addColumn(Student::getLastName).setId("lastnameId").setCaption("Last Name");
        addComponent(addBtn);
        setComponentAlignment(addBtn,Alignment.TOP_RIGHT);
        addComponent(mainGrid);
    }

    private void initStudentEditDialog(){
        studentEditDialog.setResizable(false);
        studentEditDialog.setClosable(true);
        studentEditDialog.setModal(true);
        studentEditDialog.setWidth(400f, Unit.PIXELS);

        FormLayout dialogLayout = new FormLayout();
        dialogLayout.setMargin(true);

        TextField lastNameField = new TextField("Last Name");
        TextField firstNameField = new TextField("First Name");

        ComboBox<Sex> sexComboBox = new ComboBox<>("Gender");

        Button okBtn = new Button("OK");

        dialogLayout.addComponents(lastNameField, firstNameField, sexComboBox, okBtn);
        studentEditDialog.setContent(dialogLayout);
    }

}
