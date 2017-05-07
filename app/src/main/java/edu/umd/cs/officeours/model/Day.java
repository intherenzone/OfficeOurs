package edu.umd.cs.officeours.model;


import java.io.Serializable;

class Day implements Serializable {
    private int startTime;
    private int endTime;
    private DayEnum dayEnum;

    Day(DayEnum dayEnum){
        this.dayEnum = dayEnum;
    }

    boolean setTimeSlot(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
        return true;
    }

    int getStartTime() {
        return startTime;
    }

    int getEndTime() {
        return endTime;
    }

    DayEnum getDayEnum(){
        return this.dayEnum;
    }


}
