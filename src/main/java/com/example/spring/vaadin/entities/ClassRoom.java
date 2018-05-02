package com.example.spring.vaadin.entities;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringComponent
@UIScope
public class ClassRoom {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     row - ряд - значит вертикальный ряд(колонка)
     deskLine - парта - ряд в театральном понятии
     */
    private Desk[][] classDesks = new Desk[][]
            {{new Desk(3, 2), new Desk(1,1), new Desk(2, 3)},
                    {new Desk(4, 3), new Desk(2,2), new Desk(3, 4)},
                    {new Desk(5, 4), new Desk(3,3), new Desk(4, 5)}};

    public Desk[][] getClassDesks() {
        return classDesks;
    }

    /**
     * Разместить студентов в том порядке, как выстроены в листе.
     * @param studentsList - students List
     */
    public void accomodateAsOrdered(@NotNull List<Student> studentsList){
        logger.debug("Accomodate As Ordered");
        if (studentsList == null || studentsList.isEmpty()) {
            logger.warn("Empty list of students!");
            return;
        }

        studentsList.forEach(this::accomodateToFirstEmptySeatInClass);
    }

    public void accomodateToFirstEmptySeatInClass(@NotNull Student student){
        if (student == null)
            return;

        for (int i = 0; i <= 2; i++){
            for(int j = 0; j <= 2; j++){
                Seat candidateSeat = classDesks[i][j].getFirstFreeSeat();
                if (candidateSeat != null) {
                    candidateSeat.setStudent(student);
                    logger.debug("Accommodating student " + student + " to place i: " + i + "; j: " + j);
                    return;
                }
            }
        }
        logger.error("No free places in class room!");
    }

    public void accomodate(List<Student> studentsList){
        if (studentsList == null || studentsList.isEmpty()) {
            logger.warn("Empty list of students!");
            return;
        }

    }

    public void clearClass(){
        classDesks = new Desk[][]
                {{new Desk(3, 2), new Desk(1,1), new Desk(2, 3)},
                        {new Desk(4, 3), new Desk(2,2), new Desk(3, 4)},
                        {new Desk(5, 4), new Desk(3,3), new Desk(4, 5)}};
    }

    public List<Desk> getDesksAsList(){
        List<Desk> result = new ArrayList<>();

        for (int i = 0; i <= 2; i++) {
            result.addAll(Arrays.asList(classDesks[i]).subList(0, 3));
        }


        return result;
    }

}
