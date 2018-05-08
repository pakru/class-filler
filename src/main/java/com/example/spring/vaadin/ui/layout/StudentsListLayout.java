package com.example.spring.vaadin.ui.layout;

import com.example.spring.vaadin.entities.Student;
import com.example.spring.vaadin.properties.Gender;
import com.example.spring.vaadin.properties.Height;
import com.example.spring.vaadin.properties.VisionAbility;
import com.example.spring.vaadin.repo.StudentRepository;
import com.example.spring.vaadin.ui.dialog.DialogResultCallback;
import com.example.spring.vaadin.ui.dialog.StudentEditDialog;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
@UIScope
public class StudentsListLayout extends VerticalLayout {
    private final Logger logger = Logger.getLogger(this.getClass());
    private List<Student> studentList;

    @Autowired
    private StudentRepository repo;

    @Autowired
    private StudentEditDialog studentEditDialog;


    @PostConstruct
    public void init(){
        Grid<Student> mainGrid = new Grid<>(Student.class);
        studentList = repo.findAll();
        mainGrid.setWidth("100%");
        setDefaultComponentAlignment(Alignment.TOP_CENTER);

        logger.debug("Grid columns: ");
        mainGrid.getColumns().forEach(c -> logger.debug("ID: " + c.getId() + " : " + c.getCaption()));
        mainGrid.setColumnOrder("lastName", "firstName", "gender", "heightProperty", "visionAbility");

        ListDataProvider<Student> studentDataProvider = new ListDataProvider<>(studentList);
        mainGrid.setDataProvider(studentDataProvider);

        final Button addBtn = new Button();
        addBtn.setIcon(VaadinIcons.USER);
        addBtn.addClickListener(c -> {
           logger.debug("Button clicked");
//           Student addStudent = new Student();
//           addStudent.setFirstName("Petya");
//           addStudent.setLastName("Vasechkin");
//           addStudent.setGender(Gender.MALE);
//           addStudent.setHeightProperty(Height.MIDDLE);
//           addStudent.setVisionAbility(VisionAbility.OK);
//           getUI().addWindow(studentEditDialog);
//           repo.save(addStudent);
//           studentDataProvider.getItems().add(addStudent);
//           studentList = repo.findAll();

            studentEditDialog.showEditDialog(getUI(), new DialogResultCallback<Student>() {
                @Override
                public void resultOk(Student result) {
                    logger.debug("At result OK. Result: " + result);
                    repo.save(result);
                    studentDataProvider.getItems().add(result);
                    studentList = repo.findAll();
                    studentDataProvider.refreshAll();
                    logger.debug("List of students: " + studentList);
                }
                @Override
                public void resultCancel() {
                    logger.debug("At result Cancel");
                }
            });
        });

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

        ComboBox<Gender> genderComboBox = new ComboBox<>("Gender");
//        List<Gender> genderItems = new ArrayList<Gender>(){{add(Gender.MALE); add(Gender.FEMALE);}};
        genderComboBox.setItems(Gender.MALE, Gender.FEMALE);

        ComboBox<Height> heightComboBox = new ComboBox<>("Height");
        heightComboBox.setItems(Height.VERY_HI, Height.HI, Height.MIDDLE, Height.LOW);

        ComboBox<VisionAbility> visionComboBox = new ComboBox<>("Vision Ability");
        visionComboBox.setItems(VisionAbility.OK, VisionAbility.LOW, VisionAbility.VERY_LOW);

        Button okBtn = new Button("OK");

        dialogLayout.addComponents(lastNameField, firstNameField, genderComboBox, heightComboBox, visionComboBox, okBtn);
        studentEditDialog.setContent(dialogLayout);
        studentEditDialog.setCaption("Add Student");
    }

}
