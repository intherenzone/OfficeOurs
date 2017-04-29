package edu.umd.cs.officeours.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Dtrevino on 4/29/2017.
 */

public class Professor implements Serializable {
    private Day[] days;
    private String id;
    private String name;

    //This can handle professors with identical names. Might be a problem if searching by name.
    public Professor(String name){
        this.name = name;
        this.id = UUID.randomUUID().toString();
        days = new Day[7];
        int dayIndex = 0;
        for(DayEnum dayEnum : DayEnum.values()){
            days[dayIndex++] = new Day(dayEnum);
        }
    }

    public boolean setSchedule(Day day, int startTime,int endTime){
        return true;
    }


}
