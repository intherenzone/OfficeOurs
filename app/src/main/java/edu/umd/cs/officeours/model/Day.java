package edu.umd.cs.officeours.model;

/**
 * Created by Dtrevino on 4/29/2017.
 */

public class Day {
    private int startTime;
    private int endTime;
    private DayEnum dayEnum;

    public Day(DayEnum dayEnum){
        this.dayEnum = dayEnum;
    }

    public void setTimeSlot(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
