package com.example.spring.vaadin.ui.dialog;

import com.example.spring.vaadin.entities.Student;
import com.example.spring.vaadin.properties.Gender;
import com.example.spring.vaadin.properties.Height;
import com.example.spring.vaadin.properties.VisionAbility;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class StudentEditDialog extends Window {
    private final Logger logger = Logger.getLogger(this.getClass());

    private boolean isOkPressed = false;

    private TextField lastNameField;
    private TextField firstNameField;
    private ComboBox<Gender> genderComboBox;
    private ComboBox<Height> heightComboBox;
    private ComboBox<VisionAbility> visionComboBox;
    private Button okBtn;

    public StudentEditDialog() {

    }

    @PostConstruct
    public void init() {
        setResizable(false);
        setClosable(true);
        setModal(true);
        setWidth(400f, Unit.PIXELS);

        FormLayout dialogLayout = new FormLayout();
        dialogLayout.setMargin(true);

        lastNameField = new TextField("Last Name");
        firstNameField = new TextField("First Name");

        genderComboBox = new ComboBox<>("Gender");
        genderComboBox.setItems(Gender.MALE, Gender.FEMALE);

        heightComboBox = new ComboBox<>("Height");
        heightComboBox.setItems(Height.VERY_HI, Height.HI, Height.MIDDLE, Height.LOW);

        visionComboBox = new ComboBox<>("Vision Ability");
        visionComboBox.setItems(VisionAbility.OK, VisionAbility.LOW, VisionAbility.VERY_LOW);

        okBtn = new Button("OK");
        okBtn.addClickListener(e -> {
            isOkPressed = true;
            close();
        });

        dialogLayout.addComponents(lastNameField, firstNameField, genderComboBox, heightComboBox, visionComboBox, okBtn);
        setContent(dialogLayout);
        setCaption("Add Student");
    }

    public void showEditDialog(UI ui, DialogResultCallback<Student> cb){
        isOkPressed = false;
        ui.addWindow(this);

        addCloseListener(e -> {
            if(isOkPressed) {
                logger.debug("OK pressed!");
                Student result = new Student();
                result.setFirstName(firstNameField.getValue());
                result.setLastName(lastNameField.getValue());
                result.setGender(genderComboBox.getValue());
                result.setVisionAbility(visionComboBox.getValue());
                result.setHeightProperty(heightComboBox.getValue());
                cb.resultOk(result);
            }
            else
                cb.resultCancel();
        });
    }
}
